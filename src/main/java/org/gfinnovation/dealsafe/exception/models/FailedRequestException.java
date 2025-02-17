package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class FailedRequestException
 * @since v1.0 (06/02/2025)
 */
public class FailedRequestException extends SystemGlobalException {
    public FailedRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
