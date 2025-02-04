package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationBusiness
 * @since 30/10/2024
 */
public interface ComparisonSingularService extends GenericService<ComparisonSingular> {
    ComparisonSingular create(
            ComparisonSingularRecord input
    ) throws ServiceException, BadRequestException;
}
