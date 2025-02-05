package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

public class FailedRequestException extends SystemGlobalException {
    public FailedRequestException(String message) {
        super(message);
    }
}
