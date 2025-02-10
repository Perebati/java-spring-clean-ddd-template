package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ComparisonOperationBusiness
 * @since v1.0 (30/11/2024)
 */
public interface ComparisonSingularService extends GenericService<ComparisonSingular> {
    ComparisonSingular create(
            ComparisonSingularRecord input
    ) throws SystemGlobalException;
}
