package com.git.spring_boot_ddd_template.exception.models;

import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @since v1.0 (06/02/2025)
 */
public class FailedRequestException extends SystemGlobalException {
    public FailedRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
