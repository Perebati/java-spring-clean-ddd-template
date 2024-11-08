package org.gfinnovation.dealsafe._shared.application;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessAuthenticationException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.slf4j.MDC;

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
public class GenericBusinessImpl {
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyBusiness;

    protected UUID getUserId() {
        try {
            UUID user_id = UUID.fromString(MDC.get("user_id"));
            this.userBusiness.check(user_id);
            return user_id;
        } catch (Exception e) {
            throw new BusinessAuthenticationException("Something went wrong checking for user business info.", e);
        }
    }

    protected UUID getCompanyId() {
        try {
            UUID company_id = UUID.fromString(MDC.get("company_id"));
            this.companyBusiness.check(company_id);
            return company_id;
        } catch (Exception e) {
            throw new BusinessAuthenticationException("Something went wrong checking for user business info.", e);
        }
    }

    protected UUID getRequestId() {
        try {
            String requestIdStr = MDC.get("request_id");
            if (requestIdStr != null) {
                return UUID.fromString(requestIdStr);
            } else {
                throw new BusinessAuthenticationException("Request ID is null.");
            }
        } catch (IllegalArgumentException e) {
            throw new BusinessAuthenticationException("Invalid request ID format.", e);
        } catch (Exception e) {
            throw new BusinessAuthenticationException("Something went wrong checking for web request info.", e);
        }
    }

    protected void validadeBusiness(UUID user_id, UUID company_id) throws ValidationException {
        try {
            this.userBusiness.check(user_id);
            this.companyBusiness.check(company_id);
        } catch (Exception e) {
            throw new BusinessAuthenticationException("Something went wrong checking for business info.", e);
        }
    }

    protected RepositoryAuth getRepositoryAuth() {
        this.validadeBusiness(getUserId(), getCompanyId());
        return new RepositoryAuth(getUserId(), getCompanyId(), getRequestId());
    }
}
