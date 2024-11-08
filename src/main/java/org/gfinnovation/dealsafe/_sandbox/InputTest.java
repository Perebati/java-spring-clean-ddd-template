package org.gfinnovation.dealsafe._sandbox;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
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
public class InputTest {
    private final InputBusiness inputBusiness;
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyFactory;

    public InputTest(InputBusiness inputBusiness, UserBusiness userBusiness, CompanyBusiness companyFactory) {
        this.inputBusiness = inputBusiness;
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

        InputEntity inputEntity = this.inputBusiness.create(name, json);

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

        InputEntity inputEntity = this.inputBusiness.create(name, json);

        System.out.println(inputEntity.toString());

        return inputEntity;
    }
}