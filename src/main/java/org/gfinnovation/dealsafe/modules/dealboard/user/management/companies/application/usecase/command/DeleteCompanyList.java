package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;

import java.util.UUID;

public class DeleteCompanyList implements UseCase<UUID, Void>{
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