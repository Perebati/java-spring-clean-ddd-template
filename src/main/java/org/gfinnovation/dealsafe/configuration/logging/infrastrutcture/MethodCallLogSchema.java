package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * If a method throws an exception, it gets saved
 * in MongoDb. This class maps the error.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class MethodCallLogSchema
 * @since 30/10/2024
 */
@Data
@Document(collection = "mathod_call_log")
public class MethodCallLogSchema {
    private String id;
    private String methodName;
    private String className;
    private String arguments;
    private String returnType;
    private Boolean error;
    private Date timestamp;
    private String requestId;
    private String userId;
    private String companyId;
}