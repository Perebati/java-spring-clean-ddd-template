package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationBusiness
 * @since 30/10/2024
 */
public interface ComparisonSingularDomainService extends GenericService<ComparisonSingular> {
    ComparisonSingular create(ComparisonSingular.ComparisonSingularTypeEnum type, String jsonPath, String variable, UUID node_id) throws ServiceException, BadRequestException;
}
