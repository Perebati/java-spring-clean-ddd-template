package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request;

import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.springframework.lang.NonNull;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootCreationDTO
 * @since 01/11/2024
 */

public record RootCreationPredefinedInputDTO(
        @NonNull String name,
        @NonNull PredefinedTypeEnum type) {
}
