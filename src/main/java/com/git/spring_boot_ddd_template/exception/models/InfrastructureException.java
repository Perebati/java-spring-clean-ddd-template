package com.git.spring_boot_ddd_template.exception.models;

import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * InfrastructureException is for general use, throw it when you not sure
 * what caused the error on repository level.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since v1.0 (30/11/2024)
 */
public class InfrastructureException extends SystemGlobalException {
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
