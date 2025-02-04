package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReadCompanyList implements UseCase<UUID, CompanyList> {

    private final CompanyListService companyListService;

    public ReadCompanyList(CompanyListService companyListService) {
        this.companyListService = companyListService;
    }

    @Override
    public CompanyList execute(UUID input) {
        return this.companyListService.read(input);
    }
}