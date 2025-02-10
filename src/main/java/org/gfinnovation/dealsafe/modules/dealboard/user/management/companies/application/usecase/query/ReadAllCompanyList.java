package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadAllCompanyList extends UseCase<Void, List<CompanyList>, CompanyListService> {
    public ReadAllCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public List<CompanyList> execute(Void input) {
        return service.readAll().get();
    }
}
