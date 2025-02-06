package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMultiService
 * @since v1.0 (06/02/2025)
 */
public interface ComparisonMultiService extends GenericService<ComparisonMulti> {
    ComparisonMulti create(
            ComparisonMultiRecord input
    ) throws SystemGlobalException;
}
