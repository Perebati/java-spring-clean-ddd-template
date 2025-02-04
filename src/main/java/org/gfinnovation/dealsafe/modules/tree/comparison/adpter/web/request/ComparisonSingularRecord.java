package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record ComparisonCreationDTO
 * @since 04/11/2024
 */
public record ComparisonSingularRecord(ComparisonSingular.ComparisonSingularTypeEnum type,
                                       String jsonPath,
                                       String variable,
                                       UUID parentId,
                                       NodeTreeIf.SetNode position) {
}
