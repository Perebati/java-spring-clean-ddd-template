package org.gfinnovation.dealsafe.tests.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericRepository;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.tests._shared.GenericRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RootTreeStaticRepositoryTest extends GenericRepositoryTest<RootTreeStaticEntity> {
    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CompanyBusiness companyBusiness;

    @Autowired
    private TreeFactory treeFactory;

    @Autowired
    private TreeRepository treeRepository;

    @Override
    protected RootTreeStaticEntity createEntity() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba júnior");
        CompanyEntity companyTest = this.companyBusiness.create("Taba júnior", Set.of(userTest.getId()));
        return this.treeFactory.getRootTreeFactory().produce(userTest.getId(), companyTest.getId(), "Teste", PredefinedTypeEnum.TESTE);
    }

    @Override
    protected GenericRepository<RootTreeStaticEntity> createRepository() {
        return (GenericRepository<RootTreeStaticEntity>) this.treeRepository.getRootTreeStaticRepository();
    }
}
