package assets;

import org.gfinnovation.dealsafe._shared.GenericCRUDTest;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.CompanySchema;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.authentication.company.application.business.CompanyBusinessImpl;
import org.gfinnovation.dealsafe.domains.authentication.user.application.business.UserCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CompanyBusinessTest extends GenericCRUDTest<CompanyEntity, CompanySchema, CompanyBusinessImpl> {
    @Autowired
    private CompanyBusinessImpl companyBusinessImpl;

    @Autowired
    private UserCRUD userBusiness;

    @Override
    protected CompanyEntity createEntity() {
        UserEntity user = this.userBusiness.create(new UserEntity("Ronivaldo"));
        return new CompanyEntity("TABAJARA", user);
    }

    @Override
    protected CompanyBusinessImpl createService() {
        return companyBusinessImpl;
    }
}
