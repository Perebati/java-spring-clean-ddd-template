package org.gfinnovation.dealsafe._sandbox;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputTest
 * @since 30/10/2024
 */
@RestController
@RequestMapping("teste/input")
@Tag(name = "Teste")
public class InputTest {
    private final InputService inputService;
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyFactory;

    public InputTest(InputService inputService, UserBusiness userBusiness, CompanyBusiness companyFactory) {
        this.inputService = inputService;
        this.userBusiness = userBusiness;
        this.companyFactory = companyFactory;
    }

    @PostMapping
    public InputEntity create() throws BadRequestException {
        UserEntity createdUser = this.userBusiness.create("Lucas Teste");

        CompanyEntity createdCompany = this.companyFactory.create("Teste", Collections.singleton(createdUser.getId()));

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

        String name = "jsonTeste";

        InputEntity inputEntity = this.inputService.create(name, json);

        System.out.println(inputEntity.toString());

        return inputEntity;
    }

    @PutMapping()
    public InputEntity create2() throws BadRequestException {

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
                      "teste":{
                          "teste": "teste"
                      }
                   }
                }
                """;

        String name = "jsonTeste";

        InputEntity inputEntity = this.inputService.create(name, json);

        System.out.println(inputEntity.toString());

        return inputEntity;
    }
}