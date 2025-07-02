package com.git.spring_boot_ddd_template.exception.models;

import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since 08/11/2024
 */
public class DomainException extends SystemGlobalException {
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
