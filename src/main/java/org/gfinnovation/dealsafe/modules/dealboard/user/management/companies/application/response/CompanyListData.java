package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.response;

import java.util.List;

public record CompanyListData(String name, List<String> cnpjs) {
}
