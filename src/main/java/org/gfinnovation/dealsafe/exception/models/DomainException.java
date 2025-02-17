package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class DomainException
 * @since 08/11/2024
 */
public class DomainException extends SystemGlobalException {
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
