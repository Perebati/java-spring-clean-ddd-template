package org.gfinnovation.dealsafe.tests.repository;

import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.modules.input.infrastructure.repository.interfaces.InputRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class InputRepositoryTest extends GenericBusinessRepositoryTest<Input> {

    String json = """
            {
              "nome": "João",
              "idade": 30,
              "endereco": {
                "rua": "Rua A",
                "bairro": "Centro"
              },
              "telefone": ["123456789", "987654321"],
              "teste": {
                  "teste":{
                      "teste": "teste"
                  }
               }
            }
            """;

    @Autowired
    private InputRepository inputRepository;

    @Autowired
    private InputFactory inputFactory;

    @Override
    protected Input createEntity() {
        return this.inputFactory.produce("TestInputH2", json);
    }

    @Override
    protected InputRepository createRepository() {
        return this.inputRepository;
    }
}