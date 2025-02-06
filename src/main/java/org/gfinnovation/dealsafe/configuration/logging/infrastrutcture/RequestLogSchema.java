package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Every single requisition is saved in this system.
 * This class maps what it gets saved.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RequestLogSchema
 * @since v1.0 (30/11/2024)
 */
@Data
@Document("request_log")
public class RequestLogSchema {
    private String id;
    private String userId;
    private String companyId;
    private String requestType;
    private Date timestamp;
    private String uri;
    private String requestBody;
    private String headers;
}
