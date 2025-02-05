package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * ServiceException is for general use, throw it when you not sure
 * what caused the error on business level.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ServiceException
 * @since 30/10/2024
 */
public class ApplicationException extends SystemGlobalException {
    public ApplicationException(String message) {
        super(message);
    }
}
