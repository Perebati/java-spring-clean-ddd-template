package assets;


import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.authentication.company.application.business.CompanyBusinessImpl;
import org.gfinnovation.dealsafe.domains.authentication.user.application.business.UserCRUD;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.OperationComparisonSchema;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.nodes.application.business.RootTreeCRUD;
import org.gfinnovation.dealsafe.domains.tree.nodes.entity.node.factory.NodeTreeFactoryImpl;
import org.gfinnovation.dealsafe.domains.tree.operation.application.business.OperationCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class OperationServiceTest extends GenericCRUDTest<ComparisonOperationEntity, OperationComparisonSchema, OperationCRUD> {
    @Autowired
    private OperationCRUD operationService;

    @Autowired
    private RootTreeCRUD rootTreeBusiness;

    @Autowired
    private NodeTreeFactoryImpl nodeTreeFactoryImpl;

    @Autowired
    private UserCRUD userBusiness;

    @Autowired
    private CompanyBusinessImpl companyBusinessImpl;

    @Override
    protected ComparisonOperationEntity createEntity() {
        UserEntity userEntity = this.userBusiness.create(new UserEntity("Ronivaldo"));
        CompanyEntity companyEntity = this.companyBusinessImpl.create(new CompanyEntity("TABAJARA", userEntity));
        RootTreeEntity rootTreeEntity = this.rootTreeBusiness.create(new RootTreeEntity(userEntity.getId(), companyEntity.getId(), "Root 01"));
        NodeTreeEntity nodeTreeEntity = this.nodeTreeFactoryImpl.createNode("Nó 01", 0, userEntity.getId(), companyEntity.getId(), rootTreeEntity);
        return new ComparisonOperationEntity(userEntity.getId(), companyEntity.getId(), ComparisonTypeEnum.EQUAL, "path", Set.of("example"));
    }

    @Override
    protected OperationCRUD createService() {
        return this.operationService;
    }
}
