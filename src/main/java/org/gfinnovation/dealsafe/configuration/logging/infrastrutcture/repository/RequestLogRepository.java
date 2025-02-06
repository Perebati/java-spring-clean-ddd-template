package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository;

import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RequestLogRepository
 * @since v1.0 (30/11/2024)
 */
@Profile({"dev", "prod"})
public interface RequestLogRepository extends MongoRepository<RequestLogSchema, String> {
}
