package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;

import java.util.List;

public interface CompanyListFactory {
    CompanyList produce(String name, List<String> cnpjs) throws SystemGlobalException;

    CompanyList produce(String name) throws SystemGlobalException;
}
