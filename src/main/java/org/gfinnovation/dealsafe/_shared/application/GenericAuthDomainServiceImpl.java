package org.gfinnovation.dealsafe._shared.application;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.exception.models.FailedRequestException;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * This class handles auth operations using MDC.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericAuthDomainServiceImpl
 * @since v1.0 (24/01/2025)
 */
abstract class GenericAuthDomainServiceImpl {
    protected UUID getUserId() throws SystemGlobalException {
        try {
            String userIdStr = MDC.get("userId");

            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new BadRequestException("UserId in request is null!");
            }

            return UUID.fromString(userIdStr);
        } catch (BadRequestException | IllegalArgumentException e) {
            throw new FailedRequestException("Invalid userId format!");
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking for user business info.");
        }
    }

    protected UUID getWhitelabelId() throws SystemGlobalException {
        try {
            String userIdStr = MDC.get("whitelabelId");

            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new BadRequestException("WhitelabelId in request is null!");
            }

            return UUID.fromString(userIdStr);
        } catch (BadRequestException | IllegalArgumentException e) {
            throw new FailedRequestException("Invalid whitelabelId format!");
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking for whitelabel business info.");
        }
    }

    protected UUID getRequestId() throws SystemGlobalException {
        try {
            String requestIdStr = MDC.get("requestId");
            return UUID.fromString(requestIdStr);
        } catch (IllegalArgumentException e) {
            throw new FailedRequestException("Invalid request ID format.");
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking for web request info.");
        }
    }

    protected RepositoryAuth getRepositoryAuth() throws BadRequestException {
        return new RepositoryAuth(getUserId(), getWhitelabelId(), getRequestId());
    }
}
