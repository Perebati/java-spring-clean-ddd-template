package org.gfinnovation.dealsafe.logging.infrastrutcture.repository;

import org.gfinnovation.dealsafe.logging.infrastrutcture.MethodCallLogSchema;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class MethodCallLogRepository
 * @since v1.0 (30/11/2024)
 */
@Profile({"dev", "prod"})
public interface MethodCallLogRepository extends MongoRepository<MethodCallLogSchema, String> {
}
