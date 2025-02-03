package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

import java.util.UUID;

public record ComparisonCustomListRecord(ComparisonMulti.ComparisonMultiTypeEnum type,
                                         String jsonPath,
                                         UUID comparisonListId,
                                         UUID nodeId) {}
