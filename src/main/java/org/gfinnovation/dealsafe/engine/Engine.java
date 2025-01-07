package org.gfinnovation.dealsafe.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.gfinnovation.dealsafe._sandbox.Neo4jTest;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * It receives two trees. One is input the other one is a validation tree.
 * This engine run through the validation tree making operations based on the input tree.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Engine
 * @since 30/10/2024
 */

@Component
public class Engine {
    private final Neo4jTest neo4jTest;

    public Engine(Neo4jTest neo4jTest) {
        this.neo4jTest = neo4jTest;
    }

    /**
     * This is an early version of that validates a json based on a tree previously created.
     * Still in beta, it will change in the future.
     *
     * @param root             Validation tree.
     * @param objectToValidate Json to validate.
     * @return boolean
     * @throws NoSuchMethodException     Thrown when the type in the validation tree doesn't correlate to exists types defined in this system.
     * @throws InvocationTargetException Thrown when a null pointer returns of a json reading.
     * @throws IllegalAccessException    Thrown when an internal Java error occurs.
     * @throws InstantiationException    Thrown when is not possible to get a class via a name of type String.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public boolean bfsValidation(JsonNode root, JsonNode objectToValidate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Queue<JsonNode> queue = new LinkedList<>();

        this.neo4jTest.storeUserInput(objectToValidate.toString());

        if (root.has("nodes")) {
            for (JsonNode node : root.get("nodes")) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            JsonNode currentNode = queue.poll();
            if (currentNode.has("operations")) {
                JsonNode operations = currentNode.get("operations");
                for (JsonNode operation : operations) {
                    String jsonVariablePath = operation.get("jsonVariablePath").asText();
                    ArrayNode expectedVarsNode = (ArrayNode) operation.get("expectedVars");
                    Set<String> expectedValues = new HashSet<>();
                    expectedVarsNode.forEach(node -> expectedValues.add(node.asText()));

                    ComparisonTypeEnum getType = ComparisonTypeEnum.valueOf(String.valueOf(operation.get("comparisonTypeEnum").asText()));
                    Class<?> clazz = getType.getOperationClass();
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    Method equalComparisonMethod = clazz.getMethod("doOperation", String.class, String.class);

                    String actualValue = objectToValidate.at(jsonVariablePath).asText();

                    for (String expectedValue : expectedValues) {
                        Boolean result = (Boolean) equalComparisonMethod.invoke(instance, actualValue, expectedValue);
                        if (!result) {
                            return false;
                        }
                    }

                    if (operation.has("actions")) {
                        JsonNode actions = operation.get("actions");
                        for (JsonNode action : actions) {
                            String url = action.get("url").asText();
                            HttpClient client = HttpClient.newHttpClient();
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(url))
                                    .GET()
                                    .build();

                            try {
                                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                                if (response.statusCode() == 200) {
                                    System.out.println("Resposta: " + response.body());
                                } else {
                                    System.out.println("Falha na requisição, status: " + response.statusCode());
                                }
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            if (currentNode.has("children")) {
                for (JsonNode child : currentNode.get("children")) {
                    queue.add(child);
                }
            }
        }


        return true;
    }
}
