package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteCompanyList implements UseCase<UUID, Void> {
    private final CompanyListService companyListService;

    public DeleteCompanyList(CompanyListService companyListService) {
        this.companyListService = companyListService;
    }

    @Override
    public Void execute(UUID input) {
        this.companyListService.deleteAsync(input);
        return null;
    }
}