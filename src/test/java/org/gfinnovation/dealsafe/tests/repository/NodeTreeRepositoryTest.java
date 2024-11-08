package org.gfinnovation.dealsafe.tests.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.entity.repository.GenericRepository;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class NodeTreeRepositoryTest extends GenericRepositoryTest<NodeTreeEntity> {
    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private CompanyBusiness companyBusiness;
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private TreeFactory treeFactory;

    @Override
    protected NodeTreeEntity createEntity() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba júnior", Set.of(userTest.getId()));
        return this.treeFactory.getNodeTreeFactory().produce(userTest.getId(), companyTest.getId(), "Teste", 0);
    }

    @Override
    protected GenericRepository<NodeTreeEntity> createRepository() {
        return (GenericRepository<NodeTreeEntity>) this.treeRepository.getNodeTreeRepository();
    }
}
