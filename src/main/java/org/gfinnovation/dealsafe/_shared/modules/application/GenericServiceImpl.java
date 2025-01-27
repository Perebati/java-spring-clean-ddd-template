package org.gfinnovation.dealsafe._shared.modules.application;

import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceAuthenticationException;
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
public class GenericServiceImpl {
    protected UUID getUserId() {
        try {
            return UUID.fromString(MDC.get("userId"));
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for user business info.", e);
        }
    }

    protected UUID getWhitelabelId() {
        try {
            return UUID.fromString(MDC.get("whitelabelId"));
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for user business info.", e);
        }
    }

    protected UUID getRequestId() {
        try {
            String requestIdStr = MDC.get("requestId");
            if (requestIdStr != null) {
                return UUID.fromString(requestIdStr);
            } else {
                throw new ServiceAuthenticationException("Request ID is null.");
            }
        } catch (IllegalArgumentException e) {
            throw new ServiceAuthenticationException("Invalid request ID format.", e);
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for web request info.", e);
        }
    }

    protected RepositoryAuth getRepositoryAuth() {
        return new RepositoryAuth(getUserId(), getWhitelabelId(), getRequestId());
    }
}
