package org.gfinnovation.dealsafe.modules.tree.condition.adpter.dto.request;

import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.enums.ComparisonSingularTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record ComparisonCreationDTO
 * @since 04/11/2024
 */
public record ComparisonCreationDTO(ComparisonSingularTypeEnum type, String jsonPath, String variable, UUID node_id) {
}
