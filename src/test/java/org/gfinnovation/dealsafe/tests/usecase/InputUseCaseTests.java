package org.gfinnovation.dealsafe.tests.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.application.usecase.command.CreateInputUseCase;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
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
 * @class CreateInputTest
 * @since v1.0 (07/02/2025)
 */

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("UseCase: InputCreate")
public class InputUseCaseTests extends GenericTest {
    @Autowired
    private CreateInputUseCase createInputUseCase;

    @Test
    @DisplayName("Should create an input and then validate it.")
    @Transactional
    public void test() throws JsonProcessingException, SystemGlobalException {
        String json = """
                {
                  "nome": "João",
                  "idade": 30,
                  "CPF": 11330176650,
                  "endereco": {
                    "rua": "Rua A",
                    "bairro": "Centro"
                  },
                  "telefone": ["123456789", "987654321"],
                  "teste": {
                      "teste": {
                          "teste": "teste"
                      }
                   }
                }
                """;

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(json);

        Input input = this.createInputUseCase.execute(new CreateInputData("Teste", rootNode));
        assertNotNull(input.getId());
        assertEquals("Teste", input.getName());
    }
}
