package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository;

import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.MethodCallLogSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface MethodCallLogRepository
 * @authorNote n/a
 * @since 30/10/2024
 */
public interface MethodCallLogRepository extends MongoRepository<MethodCallLogSchema, String> {
}
