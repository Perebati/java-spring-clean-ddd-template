package org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationFactory
 * @since 30/10/2024
 */

public interface ComparisonOperationFactory {
    ComparisonOperationEntity produce(UUID user_id, UUID company_id, ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws RuntimeException, BadRequestException;

}
