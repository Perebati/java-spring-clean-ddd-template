package com.git.spring_boot_ddd_template.logging.infrastrutcture.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.git.spring_boot_ddd_template.logging.infrastrutcture.RequestLogSchema;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since v1.0 (30/11/2024)
 */
@Profile({"dev", "prod"})
public interface RequestLogRepository extends MongoRepository<RequestLogSchema, String> {
}
