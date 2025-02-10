package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase2Inputs;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateCompanyList extends UseCase2Inputs<UUID, CompanyListData, CompanyList, CompanyListService> {
    public UpdateCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public CompanyList execute(UUID id, CompanyListData input) {
        return this.service.updateCompanyList(id, input);
    }
}