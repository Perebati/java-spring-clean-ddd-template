package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record NodeCreationDTO
 * @since 04/11/2024
 */

public record NodeCreationDTO(String name, UUID parent_id) {
}
