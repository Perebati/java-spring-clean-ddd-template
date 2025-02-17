package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * ApplicationException is for general use, throw it when you not sure
 * what caused the error on business level.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ApplicationException
 * @since v1.0 (30/11/2024)
 */
public class ApplicationException extends SystemGlobalException {
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
