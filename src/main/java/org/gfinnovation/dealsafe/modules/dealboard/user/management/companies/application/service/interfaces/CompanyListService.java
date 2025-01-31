package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;

import java.util.List;
import java.util.UUID;

public interface CompanyListService extends GenericService<CompanyList> {
    CompanyList createCompanyList(String name, List<String> cnpjs);
}
