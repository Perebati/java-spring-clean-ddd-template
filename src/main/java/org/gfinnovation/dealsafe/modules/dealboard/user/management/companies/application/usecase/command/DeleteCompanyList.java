package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteCompanyList extends NullOutputUseCase<UUID, CompanyListService> {
    protected DeleteCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public void execute(UUID input) {
        this.service.deleteAsync(input);
    }
}