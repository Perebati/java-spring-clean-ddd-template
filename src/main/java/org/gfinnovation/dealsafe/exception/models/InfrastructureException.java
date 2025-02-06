package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * InfrastructureException is for general use, throw it when you not sure
 * what caused the error on repository level.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InfrastructureException
 * @since v1.0 (30/11/2024)
 */
public class InfrastructureException extends SystemGlobalException {
    public InfrastructureException(String message) {
        super(message);
    }
}
