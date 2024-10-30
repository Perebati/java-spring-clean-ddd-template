package org.gfinnovation.dealsafe.configuration.exception.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GlobalException
 * @authorNote It should be used to save an error on the db.
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GlobalException extends Exception {
    private String methodId;
    private String userId;
    private String companyId;
    private String requestId;

    public GlobalException(String methodId, String userId, String companyId, String requestId, Exception cause) {
        super(cause.getMessage(), cause);
        this.methodId = methodId;
        this.userId = userId;
        this.companyId = companyId;
        this.requestId = requestId;
    }
}
