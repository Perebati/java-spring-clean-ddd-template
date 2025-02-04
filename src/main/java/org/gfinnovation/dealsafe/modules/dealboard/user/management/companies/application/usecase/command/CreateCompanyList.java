package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

@Component
public class CreateCompanyList implements UseCase<CompanyListData, CompanyList> {

    private final CompanyListService companyListService;

    public CreateCompanyList(CompanyListService companyListService) {
        this.companyListService = companyListService;
    }

    @Override
    public CompanyList execute(CompanyListData input) {
        return this.companyListService.createCompanyList(input.name(), input.cnpjs());
    }
}