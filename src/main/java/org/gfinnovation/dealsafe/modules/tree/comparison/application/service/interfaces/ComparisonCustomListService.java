package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListService
 * @since v1.0 (06/02/2025)
 */
public interface ComparisonCustomListService extends GenericService<ComparisonCustomList> {
    ComparisonCustomList createComparison(ComparisonCustomListRecord request,
                                          Boolean keepHistory) throws SystemGlobalException;
}
