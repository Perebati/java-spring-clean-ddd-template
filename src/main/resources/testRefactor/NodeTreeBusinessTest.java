package assets;

import org.gfinnovation.dealsafe._shared.GenericCRUDTest;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.authentication.company.application.business.CompanyBusinessImpl;
import org.gfinnovation.dealsafe.modules.authentication.user.application.business.UserCRUD;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.gateway.persistence.NodeTreeSchema;
import org.gfinnovation.dealsafe.modules.tree.nodes.application.business.NodeTreeCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class NodeTreeBusinessTest extends GenericCRUDTest<NodeTreeEntity, NodeTreeSchema, NodeTreeCRUD> {
    @Autowired
    private NodeTreeCRUD nodeTreeBusiness;

    @Autowired
    private UserCRUD userBusiness;

    @Autowired
    private CompanyBusinessImpl companyBusinessImpl;

    @Override
    protected NodeTreeEntity createEntity() {
        UserEntity user = this.userBusiness.create(new UserEntity("Ronivaldo"));
        CompanyEntity companyEntity = this.companyBusinessImpl.create(new CompanyEntity("TABAJARA", user));
        return new NodeTreeEntity(user.getId(), companyEntity.getId(), "Nó 01", 0);
    }

    @Override
    protected NodeTreeCRUD createService() {
        return this.nodeTreeBusiness;
    }
}
