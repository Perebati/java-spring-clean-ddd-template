package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

import java.util.List;
import java.util.UUID;

public record ComparisonMultiRecord(ComparisonMulti.ComparisonMultiTypeEnum type,
                                    String jsonPath,
                                    List<String> variables,
                                    UUID nodeId) {}
