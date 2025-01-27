package brokentests;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.modules.input.domain.repository.InputRepository;
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
 * @class InputRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class InputRepositoryTest extends GenericBusinessRepositoryTest<InputEntity> {
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
    private InputRepository inputRepository;

    @Autowired
    private InputFactory inputFactory;

    private Map.Entry<UserEntity, CompanyEntity> auth;

    @PostConstruct
    public void init() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba Júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba Júnior", Set.of(userTest.getId()));
        this.auth = Map.entry(userTest, companyTest);
    }

    @Override
    protected RepositoryAuth createRepositoryAuth() {
        return new RepositoryAuth(auth.getKey().getId(), auth.getValue().getId(), UUID.randomUUID());
    }

    @Override
    protected InputEntity createEntity() {
        return this.inputFactory.produce("Input Teste", json);
    }

    @Override
    protected InputRepository createRepository() {
        return this.inputRepository;
    }
}