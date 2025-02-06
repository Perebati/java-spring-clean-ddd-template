package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @record RootCreationDynamicInputDTO
 * @since 04/11/2024
 */

public record RootCreationDynamicInputDTO(
        @NonNull String name,
        @NonNull UUID dynamicInput_id) {
}
