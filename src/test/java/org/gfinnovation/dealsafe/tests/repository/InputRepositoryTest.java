package org.gfinnovation.dealsafe.tests.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.domains.entity.repository.GenericRepository;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.tests._shared.GenericRepositoryTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class InputRepositoryTest extends GenericRepositoryTest<InputEntity> {
    /**
     * @return
     * @throws BadRequestException
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    protected InputEntity createEntity() throws BadRequestException {
        return null;
    }

    /**
     * @return
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    protected GenericRepository<InputEntity> createRepository() {
        return null;
    }
//    String json = """
//            {
//              "nome": "João",
//              "idade": 30,
//              "endereco": {
//                "rua": "Rua A",
//                "bairro": "Centro"
//              },
//              "telefone": ["123456789", "987654321"],
//              "teste": {
//                  "teste":{
//                      "teste": "teste"
//                  }
//               }
//            }
//            """;
//    @Autowired
//    private UserBusiness userBusiness;
//    @Autowired
//    private CompanyBusiness companyBusiness;
//    @Autowired
//    private InputRepository inputRepository;
//    @Autowired
//    private InputFactory inputFactory;
//
//    @Override
//    protected InputEntity createEntity() throws BadRequestException {
//        UserEntity userTest = this.userBusiness.create("Taba júnior");
//        CompanyEntity companyTest = this.companyBusiness.create("Taba júnior", Set.of(userTest.getId()));
//        return this.inputFactory.produce(userTest.getId(), companyTest.getId(), "Input Teste", json);
//    }
//
//    @Override
//    protected GenericRepository<InputEntity> createRepository() {
//        return this.inputRepository;
//    }
}