package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record NodeCreationDTO
 * @since 04/11/2024
 */

public record NodeCreationDTO(@NonNull String name,
                              @NonNull UUID parent_id,
                              NodeTreeIf.SetNode position) {
}