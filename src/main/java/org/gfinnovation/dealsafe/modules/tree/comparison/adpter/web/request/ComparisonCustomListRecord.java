package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record ComparisonCustomListRecord(@NonNull String jsonPath,
                                         @NonNull UUID comparisonListId,
                                         @NonNull UUID parentId,
                                         NodeTreeIf.SetNode position) {
}
