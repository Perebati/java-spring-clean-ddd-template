package brokentests;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeStaticRepository;
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
 * @class RootTreeRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeStaticRepositoryTest extends GenericBusinessRepositoryTest<RootTreeStatic> {
    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CompanyBusiness companyBusiness;

    @Autowired
    private RootTreeFactory rootTreeFactory;

    @Autowired
    private RootTreeStaticRepository rootTreeStaticRepository;

    private Map.Entry<UserEntity, CompanyEntity> auth;


    @PostConstruct
    public void init() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba Júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba Júnior", Set.of(userTest.getId()));
        this.auth = Map.entry(userTest, companyTest);
    }

    @Override
    protected RepositoryAuth createRepositoryAuth() {
        return new RepositoryAuth(auth.getValue().getId(), auth.getValue().getId(), UUID.randomUUID());
    }


    @Override
    protected RootTreeStatic createEntity() {
        String name = "Teste";
        return this.rootTreeFactory.produce(name, PredefinedTypeEnum.TESTE);
    }

    @Override
    protected RootTreeStaticRepository createRepository() {
        return this.rootTreeStaticRepository;
    }
}
