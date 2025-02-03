package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadAllCompanyList implements UseCase<Void, List<CompanyList>> {
    private final CompanyListService companyListService;

    public ReadAllCompanyList(CompanyListService companyListService) {
        this.companyListService = companyListService;
    }

    @Override
    public List<CompanyList> execute(Void input) {
        return companyListService.readAll().get();
    }
}
