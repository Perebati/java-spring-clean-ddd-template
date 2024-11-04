package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request;

import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootCreationDTO
 * @since 01/11/2024
 */

public record RootCreationPredefinedInputDTO(String name, PredefinedTypeEnum type) {
}
