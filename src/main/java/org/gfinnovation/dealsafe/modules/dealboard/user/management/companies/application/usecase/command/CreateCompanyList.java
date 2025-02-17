package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListCreationData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

@Component
public class CreateCompanyList extends UseCase<CompanyListCreationData, CompanyList, CompanyListService> {
    public CreateCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public CompanyList execute(CompanyListCreationData input) throws SystemGlobalException {
        return this.service.createCompanyList(input.name(), input.cnpjs());
    }
}