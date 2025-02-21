package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListRecord
 * @since v1.0 (06/02/2025)
 */
public record ComparisonCustomListRecord(
                                         @NonNull ComparisonCustomList.ComparisonCustomListEnum type,
                                         @NonNull String jsonPath,
                                         @NonNull UUID comparisonListId,
                                         @NonNull UUID parentId,
                                         NodeTreeIf.SetNode position) {
}
