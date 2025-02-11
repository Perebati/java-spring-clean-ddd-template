package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindCompanyList extends UseCase<UUID, Optional<CompanyList>, CompanyListService> {
    public FindCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public Optional<CompanyList> execute(UUID input) {
        return this.service.findById(input);
    }
}