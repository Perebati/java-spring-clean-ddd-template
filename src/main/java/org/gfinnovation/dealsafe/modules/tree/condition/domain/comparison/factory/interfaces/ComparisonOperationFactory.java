package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.factory.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.Comparison;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.enums.ComparisonTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationFactory
 * @since 30/10/2024
 */

public interface ComparisonOperationFactory {
    Comparison produce(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws FactoryException, BadRequestException;
}
