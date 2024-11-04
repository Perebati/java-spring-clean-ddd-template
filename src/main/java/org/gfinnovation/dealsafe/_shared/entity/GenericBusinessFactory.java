package org.gfinnovation.dealsafe._shared.entity;

import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;

import java.util.UUID;

/**
 * Every single factory that is not linked to authentication entities should extend
 * from this.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessFactory
 * @since 30/10/2024
 */

@RequiredArgsConstructor
public class GenericBusinessFactory {
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyBusiness;

    public void validadeBusiness(UUID user_id, UUID company_id) {
        this.userBusiness.check(user_id);
        this.companyBusiness.check(company_id);
    }
}
