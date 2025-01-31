package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory;

import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.factory.interfaces.CompanyListFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CompanyFactoryImpl implements CompanyListFactory {
    public CompanyList produce(String name, List<String> cnpjs) throws FactoryException {
        try {
            return new CompanyList(name, cnpjs);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a company list.", e);
        }
    }

    public CompanyList produce(String name) throws FactoryException {
        try {
            return new CompanyList(name);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a company list.", e);
        }
    }
}
