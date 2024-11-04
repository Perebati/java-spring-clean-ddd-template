package org.gfinnovation.dealsafe.domains.tree.infrastructure.inbound.dto.request;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record RootCreationDynamicInputDTO
 * @since 04/11/2024
 */
public record RootCreationDynamicInputDTO(String name, UUID dynamicInput_id) {
}
