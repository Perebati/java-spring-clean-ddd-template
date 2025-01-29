package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.factory.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.modules.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.enums.ComparisonSingularTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationFactory
 * @since 30/10/2024
 */

public interface ComparisonSingularFactory {
    ComparisonSingular produce(ComparisonSingularTypeEnum type, String jsonPath, String variable, UUID node_id) throws FactoryException, BadRequestException;
}
