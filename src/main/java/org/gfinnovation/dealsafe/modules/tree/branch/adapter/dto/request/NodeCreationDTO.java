package org.gfinnovation.dealsafe.modules.tree.branch.adapter.dto.request;

import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record NodeCreationDTO
 * @since 04/11/2024
 */

public record NodeCreationDTO(String name, Integer sequence, UUID parent_id, Branch.ParentType parent_type) {
}
