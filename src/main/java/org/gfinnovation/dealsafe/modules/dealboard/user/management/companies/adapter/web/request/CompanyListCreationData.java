package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CompanyListCreationData
 * @since v1.0 (10/02/2025)
 */

public record CompanyListCreationData(@NonNull String name,
                                      List<String> cnpjs) {
}
