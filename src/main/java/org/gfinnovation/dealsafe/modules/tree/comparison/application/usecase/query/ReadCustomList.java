package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadCustomList
 * @since v1.0 (06/02/2025)
 */
@Component
public class ReadCustomList {
    private static CompanyListService staticCompanyListService;

    @Autowired
    public ReadCustomList(CompanyListService companyListService) {
        staticCompanyListService = companyListService;
    }

    public static List<String> execute(UUID input) throws BadRequestException, SystemGlobalException {
        CompanyList comparisonMulti = staticCompanyListService.read(input);
        return comparisonMulti.getCnpjs();
    }
}