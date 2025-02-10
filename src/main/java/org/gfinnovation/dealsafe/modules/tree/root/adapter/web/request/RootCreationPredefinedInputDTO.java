package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request;

import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.springframework.lang.NonNull;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootCreationDTO
 * @since 01/11/2024
 */
public record RootCreationPredefinedInputDTO(
        @NonNull String name,
        @NonNull PredefinedTypeEnum type) {
}
