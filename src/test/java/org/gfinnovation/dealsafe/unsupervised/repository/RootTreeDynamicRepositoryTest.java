package org.gfinnovation.dealsafe.unsupervised.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.unsupervised._shared.GenericRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryTest
 * @authorNote n/a
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeDynamicRepositoryTest extends GenericRepositoryTest<RootTreeDynamicEntity> {
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
    private UserBusiness userBusiness;
    @Autowired
    private CompanyBusiness companyBusiness;
    @Autowired
    private TreeFactory treeFactory;
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private InputBusiness inputBusiness;

    @Override
    protected RootTreeDynamicEntity createEntity() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba júnior", Set.of(userTest.getId()));
        InputEntity inputTest = this.inputBusiness.create(userTest.getId(), companyTest.getId(), "TesteInput", json);
        return this.treeFactory.getRootTreeFactory().produce(userTest.getId(), companyTest.getId(), "Teste", inputTest.getId());
    }

    @Override
    protected RootTreeDynamicRepository createRepository() {
        return this.treeRepository.getRootTreeDynamicRepository();
    }
}
