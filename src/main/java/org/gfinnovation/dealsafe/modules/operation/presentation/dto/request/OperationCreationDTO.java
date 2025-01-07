package org.gfinnovation.dealsafe.modules.operation.presentation.dto.request;

import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record OperationCreationDTO
 * @since 04/11/2024
 */
public record OperationCreationDTO(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) {
}
