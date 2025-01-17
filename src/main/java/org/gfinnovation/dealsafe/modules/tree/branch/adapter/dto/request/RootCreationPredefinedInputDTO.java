package org.gfinnovation.dealsafe.modules.tree.branch.adapter.dto.request;

import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootCreationDTO
 * @since 01/11/2024
 */

public record RootCreationPredefinedInputDTO(String name, PredefinedTypeEnum type) {
}
