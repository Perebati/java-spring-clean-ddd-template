package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReadCompanyList extends UseCase<UUID, CompanyList, CompanyListService> {
    public ReadCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public CompanyList execute(UUID input) {
        return this.service.read(input);
    }
}