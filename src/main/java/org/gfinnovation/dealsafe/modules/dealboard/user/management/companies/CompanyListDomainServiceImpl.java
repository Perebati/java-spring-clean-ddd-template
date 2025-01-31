package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
