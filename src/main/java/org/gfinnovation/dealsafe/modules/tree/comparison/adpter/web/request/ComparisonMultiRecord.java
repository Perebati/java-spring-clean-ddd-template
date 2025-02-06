package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMultiRecord
 * @since v1.0 (06/02/2025)
 */
public record ComparisonMultiRecord(@NonNull ComparisonMulti.ComparisonMultiTypeEnum type,
                                    @NonNull String jsonPath,
                                    @NonNull List<String> variables,
                                    @NonNull UUID parentId,
                                    NodeTreeIf.SetNode position) {
}
