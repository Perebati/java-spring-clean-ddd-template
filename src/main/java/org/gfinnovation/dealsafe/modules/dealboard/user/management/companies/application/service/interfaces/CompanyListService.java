package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;

import java.util.List;

public interface CompanyListService extends GenericService<CompanyList> {
    CompanyList createCompanyList(String name, List<String> cnpjs) throws SystemGlobalException;
}
