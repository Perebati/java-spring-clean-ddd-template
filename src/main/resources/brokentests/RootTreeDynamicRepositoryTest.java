package brokentests;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeDynamicRepositoryTest extends GenericBusinessRepositoryTest<RootTreeDynamic> {
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
    private Map.Entry<UserEntity, CompanyEntity> auth;
    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private CompanyBusiness companyBusiness;

    @Autowired
    private RootTreeFactory rootTreeFactory;

    @Autowired
    private RootTreeDynamicRepository rootTreeDynamicRepository;

    @Mock
    private InputService inputService;

    @BeforeEach
    public void setUp() throws BadRequestException {
        super.setUp();
        doNothing().when(inputService).check(any(UUID.class));

        ReflectionTestUtils.setField(
                rootTreeFactory,
                "inputService",
                inputService
        );
    }

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
    protected RootTreeDynamic createEntity() {
        String name = "Teste";
        UUID dynamicInput = UUID.randomUUID();
        return this.rootTreeFactory.produce(name, dynamicInput);
    }

    @Override
    protected RootTreeDynamicRepository createRepository() {
        return this.rootTreeDynamicRepository;
    }
}
