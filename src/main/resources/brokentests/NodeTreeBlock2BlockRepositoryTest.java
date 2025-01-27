package brokentests;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBlock2BlockRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class NodeTreeBlock2BlockRepositoryTest extends GenericBusinessRepositoryTest<NodeTreeBlock> {
    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CompanyBusiness companyBusiness;

    @Autowired
    private NodeTreeBlockRepository nodeTreeBlockRepository;

    @Autowired
    private NodeTreeFactory nodeTreeFactory;

    private Map.Entry<UserEntity, CompanyEntity> auth;

    @PostConstruct
    public void init() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba Júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba Júnior", Set.of(userTest.getId()));
        this.auth = Map.entry(userTest, companyTest);
    }

    @Override
    protected NodeTreeBlock createEntity() {
        return null;
    }

    @Override
    protected GenericBusinessRepository<NodeTreeBlock> createRepository() {
        return this.nodeTreeBlockRepository;
    }

    @Override
    protected RepositoryAuth createRepositoryAuth() {
        return new RepositoryAuth(auth.getKey().getId(), auth.getValue().getId(), UUID.randomUUID());
    }
}