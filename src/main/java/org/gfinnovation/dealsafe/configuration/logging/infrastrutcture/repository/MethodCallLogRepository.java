package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository;

import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.MethodCallLogSchema;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface MethodCallLogRepository
 * @since 30/10/2024
 */
@Profile({"dev", "prod"})
public interface MethodCallLogRepository extends MongoRepository<MethodCallLogSchema, String> {
}
