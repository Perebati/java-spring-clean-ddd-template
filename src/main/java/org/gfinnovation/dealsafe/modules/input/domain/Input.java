package org.gfinnovation.dealsafe.modules.input.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe.exception.models.FailedRequestException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.exception.IllegalFieldException;
import org.gfinnovation.dealsafe.utils.annotations.Default;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class stores the structure of a dynamic input.
 * It also has methods that can validate an operation by
 * checking if the comparison field exists in the dynamic input.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InputEntity
 * @since v1.0 (30/11/2024)
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@Validated
public class Input extends GenericBusinessClass {
    @NotNull(message = "O nome do input não pode ser nulo.")
    @Size(min = 4, max = 100, message = "O nome do input deve ter entre 1 e 100 caracteres.")
    private String name;

    private HashMap<String, Object> fields = new HashMap<>();

    @Default
    public Input(
            @NotNull @Size(min = 1) String name,
            @NotNull @Size(min = 1) HashMap<String, Object> fields
    ) {
        this.name = name;
        this.fields = fields;
    }

    public Input(
            @NotNull @Size(min = 4) String name,
            @NotNull @Size(min = 4) String json
    ) throws FailedRequestException, IllegalFieldException {
        this.name = name;
        this.parseJson(json);
        validate();
    }

    private void validate() throws FailedRequestException {
        if (this.name == null || this.name.length() < 4 || this.name.length() > 100) {
            throw new FailedRequestException("O nome do input deve ter entre 4 e 100 caracteres.", null);
        }
    }


    public void parseJson(String json) throws IllegalFieldException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            mapJsonToFields("", rootNode, fields);
        } catch (Exception e) {
            throw new IllegalFieldException("Error processing Json", e);
        }
    }


    private void mapJsonToFields(String parentPath, JsonNode node, HashMap<String, Object> map) {
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = node.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = fieldsIterator.next();
            String fieldName = entry.getKey();
            JsonNode childNode = entry.getValue();

            String fullPath = parentPath.isEmpty() ? fieldName : parentPath + "." + fieldName;

            if (childNode.isObject()) {
                mapJsonToFields(fullPath, childNode, map);
            } else if (childNode.isArray()) {
                map.put(fullPath, "Array<" + getJsonNodeType(childNode.elements().next()) + ">");
            } else {
                map.put(fullPath, getJsonNodeType(childNode));
            }
        }
    }

    private String getJsonNodeType(JsonNode node) {
        if (node.isTextual()) return "String";
        if (node.isInt()) return "Integer";
        if (node.isLong()) return "Long";
        if (node.isDouble()) return "Double";
        if (node.isBoolean()) return "Boolean";
        return "Unknown";
    }

    //TODO: Estes dois métodos abaixo precisam de reajustes
    public void validateJsonPath(String jsonPath) throws IllegalFieldException {
        if (jsonPath.startsWith("/")) jsonPath = jsonPath.replaceFirst("^[/.]", "");

        jsonPath = jsonPath.replaceAll("/", ".");

        if (!fields.containsKey(jsonPath)) {
            throw new IllegalFieldException("Specified path does not exist: " + jsonPath, null);
        }
    }

    public void validateJsonPathAndType(String jsonPath, Object variable) throws IllegalFieldException {
        if (jsonPath.startsWith("/")) jsonPath = jsonPath.replaceFirst("^[/.]", "");

        jsonPath = jsonPath.replaceAll("/", ".");

        this.validateJsonPath(jsonPath);

        String expectedType = fields.get(jsonPath).toString();

        boolean isTypeValid = validateType(variable, expectedType);

        if (!isTypeValid) {
            throw new IllegalFieldException("Variable type does not match expected type: " + expectedType, null);
        }
    }

    private boolean validateType(Object variable, String expectedType) {
        return switch (expectedType) {
            case "String" -> variable instanceof String || variable instanceof Integer;
            case "Integer" -> variable instanceof Integer || variable instanceof String;
            case "Long" -> variable instanceof Long || variable instanceof String;
            case "Double" -> variable instanceof Double || variable instanceof String;
            case "Boolean" -> variable instanceof Boolean || variable instanceof String;
            case "Unknown" -> false;
            default -> {
                if (expectedType.startsWith("Array<")) {
                    yield variable instanceof Iterable<?>;
                }
                yield false;
            }
        };
    }
}