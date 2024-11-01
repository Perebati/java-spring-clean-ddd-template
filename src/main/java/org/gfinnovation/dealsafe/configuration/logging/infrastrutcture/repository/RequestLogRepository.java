package org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository;

import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RequestLogRepository
 * @since 30/10/2024
 */
public interface RequestLogRepository extends MongoRepository<RequestLogSchema, String> {
}
