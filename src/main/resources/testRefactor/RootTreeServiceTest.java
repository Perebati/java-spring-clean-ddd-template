package assets;

import org.gfinnovation.dealsafe._shared.GenericCRUDTest;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.authentication.company.application.business.CompanyBusinessImpl;
import org.gfinnovation.dealsafe.modules.authentication.user.application.business.UserCRUD;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.gateway.persistence.RootTreeSchema;
import org.gfinnovation.dealsafe.modules.tree.nodes.application.business.RootTreeCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeServiceTest extends GenericCRUDTest<RootTreeEntity, RootTreeSchema, RootTreeCRUD> {
    @Autowired
    private RootTreeCRUD rootTreeService;
    @Autowired
    private UserCRUD userBusiness;
    @Autowired
    private CompanyBusinessImpl companyBusinessImpl;


    @Override
    protected RootTreeEntity createEntity() {
        UserEntity user = this.userBusiness.create(new UserEntity("Ronivaldo"));
        CompanyEntity companyEntity = this.companyBusinessImpl.create(new CompanyEntity("TABAJARA", user));
        return new RootTreeEntity(user.getId(), companyEntity.getId(), "Root 01");
    }

    @Override
    protected RootTreeCRUD createService() {
        return rootTreeService;
    }
}
