package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory.interfaces.CompanyListFactory;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.repository.interfaces.CompanyListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CompanyListServiceImpl
        extends GenericDomainServiceImpl<CompanyList, CompanyListRepository>
        implements CompanyListService {
    private final CompanyListFactory companyListFactory;

    public CompanyListServiceImpl(
            CompanyListRepository repository, CompanyListFactory companyListFactory
    ) {
        super(repository);
        this.companyListFactory = companyListFactory;
    }

    public CompanyList createCompanyList(String name, List<String> cnpjs) {
        try {
            if(cnpjs != null && !cnpjs.isEmpty()) {
                return this.repository.createSync(this.companyListFactory.produce(name, cnpjs), getRepositoryAuth());
            } else {
                return this.repository.createSync(this.companyListFactory.produce(name), getRepositoryAuth());
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a company list.", e);
        }
    }
}