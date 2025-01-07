package org.gfinnovation.dealsafe.tests.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.tests._shared.GenericRepositoryTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeDynamicRepositoryTest extends GenericRepositoryTest<RootTreeDynamicEntity> {
    /**
     * @return
     * @throws BadRequestException
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    protected RootTreeDynamicEntity createEntity() throws BadRequestException {
        return null;
    }

    /**
     * @return
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    protected GenericRepository<RootTreeDynamicEntity> createRepository() {
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
//    private TreeFactory treeFactory;
//    @Autowired
//    private TreeRepository treeRepository;
//    @Autowired
//    private InputBusiness inputBusiness;
//
//    @Override
//    protected RootTreeDynamicEntity createEntity() throws BadRequestException {
//        UserEntity userTest = this.userBusiness.create("Taba júnior");
//        CompanyEntity companyTest = this.companyBusiness.create("Taba júnior", Set.of(userTest.getId()));
//        InputEntity inputTest = this.inputBusiness.create(userTest.getId(), companyTest.getId(), "TesteInput", json);
//        return this.treeFactory.getRootTreeFactory().produce(userTest.getId(), companyTest.getId(), "Teste", inputTest.getId());
//    }
//
//    @Override
//    protected RootTreeDynamicRepository createRepository() {
//        return this.treeRepository.getRootTreeDynamicRepository();
//    }
}
