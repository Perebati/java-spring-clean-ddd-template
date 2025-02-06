package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request;

import org.springframework.lang.NonNull;

import java.util.List;

public record CompanyListData(@NonNull String name,
                              @NonNull List<String> cnpjs) {
}
