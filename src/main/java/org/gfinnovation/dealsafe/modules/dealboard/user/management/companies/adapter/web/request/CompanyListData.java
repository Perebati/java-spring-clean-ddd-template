package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request;

import java.util.List;

public record CompanyListData(String name, List<String> cnpjs) {
}
