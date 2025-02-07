package org.gfinnovation.dealsafe.configuration.data;

import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.ErrorLogRepository;
import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.MethodCallLogRepository;
import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.RequestLogRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class MongoConfig
 * @since v1.0 (06/02/2025)
 */

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(
        basePackageClasses = {ErrorLogRepository.class, MethodCallLogRepository.class, RequestLogRepository.class}
)
public class MongoConfig {
}
