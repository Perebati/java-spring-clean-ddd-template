package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory.interfaces.CompanyListFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CompanyFactoryImpl implements CompanyListFactory {
    @Override
    public CompanyList produce(String name, List<String> cnpjs) throws SystemGlobalException {
        try {
            return new CompanyList(name, cnpjs);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a company list.");
        }
    }

    @Override
    public CompanyList produce(String name) throws SystemGlobalException {
        try {
            return new CompanyList(name);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a company list.");
        }
    }
}