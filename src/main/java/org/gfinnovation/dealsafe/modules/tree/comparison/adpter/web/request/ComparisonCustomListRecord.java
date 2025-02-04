package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

import java.util.UUID;

public record ComparisonCustomListRecord(String jsonPath,
                                         UUID comparisonListId,
                                         UUID parentId,
                                         NodeTreeIf.SetNode position) {
}
