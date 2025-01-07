package org.gfinnovation.dealsafe.modules.tree.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTreeEntity;

import java.util.UUID;

/**
 * Maps Root attributes if the input of the validation tree is predefined.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticEntity
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeStaticEntity extends RootTreeEntity {
    private PredefinedTypeEnum type;

    @Default
    public RootTreeStaticEntity(UUID userId, UUID companyId, String name, PredefinedTypeEnum type) {
        super(userId, companyId, name);
        this.type = type;
    }
}
