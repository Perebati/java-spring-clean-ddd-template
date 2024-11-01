package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Every exception thrown is this system is saved asynchronously
 * in MongoDb. This class represents a saved error.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ErrorLogSchema
 * @since 30/10/2024
 */
@Data
@Document(collection = "error_log")
public class ErrorLogSchema {
    private String id;
    private String methodCallLogId;
    private String errorMessage;
    private String errorStackTrace;
    private String errorCause;
    private Date timestamp;
    private String requestId;
    private String userId;
    private String companyId;
    private String methodId;
}