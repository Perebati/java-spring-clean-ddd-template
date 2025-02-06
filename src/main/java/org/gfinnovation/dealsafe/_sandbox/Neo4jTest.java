package org.gfinnovation.dealsafe._sandbox;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Query;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.neo4j.driver.Values.parameters;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class Neo4jTest
 * @since v1.0 (04/11/2024)
 */
@Component
@Profile("dev")
public class Neo4jTest implements AutoCloseable {
    private final Driver driver;
    private final String uri = "neo4j://localhost:7999";
    private final String user = "neo4j";
    private final String password = "password";

    public Neo4jTest() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws RuntimeException {
        driver.close();
    }

    public void printGreeting(final String message) {
        try (var session = driver.session()) {
            var greeting = session.executeWrite(tx -> {
                var query = new Query("CREATE (a:Greeting) SET a.message = $message RETURN a.message + ', from node ' + id(a)", parameters("message", message));
                var result = tx.run(query);
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        }
    }

    @Async
    public void storeUserInput(String jsonInput) {
        String companyId = "id exemplo";
        String random = UUID.randomUUID().toString();
        try (var session = driver.session()) {
            session.executeWrite(tx -> {
                var query = """
                            MERGE (c:Company {company_id: $companyId})
                            WITH c
                            CREATE (i:UserInput {name: $random, json: $jsonInput})
                            CREATE (i)-[:BELONGS_TO]->(c)
                            RETURN i
                        """;

                var result = tx.run(query, parameters("companyId", companyId, "jsonInput", jsonInput, "random", random));
                return result.single().get(0).asNode();
            });
        } catch (Exception e) {
            throw e;
        }
    }
}
