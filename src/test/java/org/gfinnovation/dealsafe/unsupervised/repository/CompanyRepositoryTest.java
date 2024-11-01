package org.gfinnovation.dealsafe.unsupervised.repository;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.company.entity.factory.CompanyFactory;
import org.gfinnovation.dealsafe.authentication.company.entity.repository.CompanyRepository;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.unsupervised._shared.GenericRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyRepositoryTest
 * @since 30/10/2024
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CompanyRepositoryTest extends GenericRepositoryTest<CompanyEntity> {

    @Autowired
    private CompanyFactory companyFactory;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserBusiness userBusiness;


    @Override
    protected CompanyEntity createEntity() throws BadRequestException {
        UserEntity userTest = this.userBusiness.create("Taba júnior");
        return this.companyFactory.createCompany("Taba júnior", Set.of(userTest.getId()));
    }

    @Override
    protected CompanyRepository createRepository() {
        return this.companyRepository;
    }
}