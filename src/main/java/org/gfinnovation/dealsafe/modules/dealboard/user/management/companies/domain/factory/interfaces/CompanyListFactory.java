package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;

import java.util.List;

public interface CompanyListFactory {
    CompanyList produce(String name, List<String> cnpjs) throws FactoryException;

    CompanyList produce(String name) throws FactoryException;
}
