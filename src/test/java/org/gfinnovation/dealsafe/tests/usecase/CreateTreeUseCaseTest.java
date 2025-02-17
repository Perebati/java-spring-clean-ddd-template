package org.gfinnovation.dealsafe.tests.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.engine.generator.application.usecase.CreateTreeUseCase;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.tests._shared.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CreateTreeUseCaseTest
 * @since v1.0 (13/02/2025)
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("UseCase: CreateTreeUseCase")
public class CreateTreeUseCaseTest extends GenericTest {
    String input = """
                    {
                      "input_type": "TESTE",
                      "name": "Root Tree Name",
                      "nodes": [
                        {
                          "name": "First Block",
                          "nodes": [
                            {
                              "conditionalNodes": [
                                {
                                  "name": "Nested Block in conditionalNodes",
                                  "nodes": []
                                }
                              ],
                              "thenNodes": [
                                {
                                  "comparisonTypeEnum": "CONTAINS",
                                  "jsonVariablePath": "/idade",
                                  "customListId": "2e058ea4-6f1a-4dc8-9fdc-bbda55f7ed80"
                                }
                              ],
                              "elseNodes": [
                                {
                                  "name": "Else Block Node",
                                  "nodes": []
                                }
                              ]
                            },
                            {
                              "comparisonTypeEnum": "CONTAINS",
                              "jsonVariablePath": "/idade",
                              "customListId": "9c1508f0-3baf-4682-91f1-4341e77e2af3"
                            }
                          ]
                        }
                      ]
                    }
            """;

    @Autowired
    private CreateTreeUseCase createTreeUseCase;

    @Transactional
    @Test
    public void testCreateTreeUseCase() throws JsonProcessingException, SystemGlobalException {
        ObjectMapper mapper = new ObjectMapper();

        RootTree<?> rootObject = mapper.readValue(input, RootTree.class);

        RootTree<?> resultRoot = this.createTreeUseCase.execute(rootObject);

        assertNotNull(resultRoot.getId());
        assertNotNull(resultRoot.getName());
        assertNotNull(resultRoot.getNodes());
        assertEquals("Root Tree Name", rootObject.getName());
        assertEquals(1, resultRoot.getNodes().size());
        assertNotNull(resultRoot.getNodes().getFirst().getId());
    }
}
