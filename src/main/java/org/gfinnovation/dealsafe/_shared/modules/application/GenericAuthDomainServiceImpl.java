package org.gfinnovation.dealsafe._shared.modules.application;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceAuthenticationException;
import org.slf4j.MDC;

import java.util.UUID;

public abstract class GenericAuthDomainServiceImpl {
    protected UUID getUserId() throws BadRequestException {
        try {
            String userIdStr = MDC.get("userId");

            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new BadRequestException("UserId in request is null!");
            }

            return UUID.fromString(userIdStr);
        } catch (BadRequestException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid userId format!", e);
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for user business info.", e);
        }
    }

    protected UUID getWhitelabelId() throws BadRequestException {
        try {
            String userIdStr = MDC.get("whitelabelId");

            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new BadRequestException("WhitelabelId in request is null!");
            }

            return UUID.fromString(userIdStr);
        } catch (BadRequestException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid whitelabelId format!", e);
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for whitelabel business info.", e);
        }
    }

    protected UUID getRequestId() {
        try {
            String requestIdStr = MDC.get("requestId");
            return UUID.fromString(requestIdStr);
        } catch (IllegalArgumentException e) {
            throw new ServiceAuthenticationException("Invalid request ID format.", e);
        } catch (Exception e) {
            throw new ServiceAuthenticationException("Something went wrong checking for web request info.", e);
        }
    }

    protected RepositoryAuth getRepositoryAuth() throws BadRequestException {
        return new RepositoryAuth(getUserId(), getWhitelabelId(), getRequestId());
    }
}
