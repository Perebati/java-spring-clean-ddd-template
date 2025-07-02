package com.git.spring_boot_ddd_template.exception;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @since v1.0 (06/02/2025)
 */
public class SystemGlobalException extends Exception {
    public SystemGlobalException(String message, Throwable cause) {
        super(message, cause);
    }
}
