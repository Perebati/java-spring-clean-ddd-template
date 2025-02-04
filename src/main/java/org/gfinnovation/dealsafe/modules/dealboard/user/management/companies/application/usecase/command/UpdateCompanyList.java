package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompanyList implements UseCase<CompanyList, CompanyList> {
    private final CompanyListService companyListService;

    public UpdateCompanyList(CompanyListService companyListService) {
        this.companyListService = companyListService;
    }

    @Override
    public CompanyList execute(CompanyList input) {
        return this.companyListService.updateSync(input);
    }
}