package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class CompanyListDomainServiceImpl
        extends GenericDomainServiceImpl<CompanyList, CompanyListRepository>
        implements CompanyListService
{
    public CompanyListDomainServiceImpl(
            CompanyListRepository repository
    ) {
        super(repository);
    }
}
