package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.modules.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.enums.ComparisonSingularTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationBusiness
 * @since 30/10/2024
 */
public interface ComparisonSingularService extends GenericService<ComparisonSingular> {
    ComparisonSingular create(ComparisonSingularTypeEnum type, String jsonPath, String variable, UUID node_id) throws ServiceException, BadRequestException;
}
