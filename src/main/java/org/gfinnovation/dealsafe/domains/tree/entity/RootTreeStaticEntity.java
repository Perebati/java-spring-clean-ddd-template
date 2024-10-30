package org.gfinnovation.dealsafe.domains.tree.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticEntity
 * @authorNote Maps Root attributes if the input of the validation tree is predefined.
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
