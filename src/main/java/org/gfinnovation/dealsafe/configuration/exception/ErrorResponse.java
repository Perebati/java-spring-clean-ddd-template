package org.gfinnovation.dealsafe.configuration.exception;

import lombok.Data;

/**
 * Always use this to return an error to the client.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ErrorResponse
 * @since 30/10/2024
 */
@Data
public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
}