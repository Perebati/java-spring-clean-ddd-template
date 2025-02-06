package org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListService
 * @since v1.0 (06/02/2025)
 */
public interface ComparisonCustomListService extends GenericService<ComparisonCustomList> {
    ComparisonCustomList create(
            ComparisonCustomList.ComparisonCustomListEnum type,
            String jsonPath,
            UUID customListId,
            UUID parentId,
            NodeTreeIf.SetNode position
    ) throws SystemGlobalException;
}
