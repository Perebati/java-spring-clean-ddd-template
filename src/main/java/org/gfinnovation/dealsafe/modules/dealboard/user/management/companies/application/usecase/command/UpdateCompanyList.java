package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompanyList extends UseCase<CompanyList, CompanyList, CompanyListService> {
    public UpdateCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public CompanyList execute(CompanyList input) {
        return this.service.update(input);
    }
}