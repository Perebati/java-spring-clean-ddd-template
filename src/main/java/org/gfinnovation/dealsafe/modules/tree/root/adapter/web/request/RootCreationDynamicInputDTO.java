package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @record RootCreationDynamicInputDTO
 * @since v1.0 (04/11/2024)
 */
public record RootCreationDynamicInputDTO(
        @NonNull String name,
        @NonNull UUID dynamicInput_id) {
}
