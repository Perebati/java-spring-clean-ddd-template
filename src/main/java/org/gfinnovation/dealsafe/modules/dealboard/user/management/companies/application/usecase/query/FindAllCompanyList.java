package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAllCompanyList extends UseCase<Void, Optional<List<CompanyList>>, CompanyListService> {
    public FindAllCompanyList(CompanyListService companyListService) {
        super(companyListService);
    }

    @Override
    public Optional<List<CompanyList>> execute(Void input) {
        return service.readAll();
    }
}
