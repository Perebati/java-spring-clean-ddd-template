package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.ComparisonTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationBusiness
 * @since 30/10/2024
 */
public interface ComparisonOperationService extends GenericService<ComparisonOperation> {
    ComparisonOperation create(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws BusinessException, BadRequestException;
}
