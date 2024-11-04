package org.gfinnovation.dealsafe.domains.tree.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.UUID;

/**
 * Maps Root attributes if the input of the validation tree is dynamic.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicEntity
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeDynamicEntity extends RootTreeEntity {
    private UUID dynamicReference;

    @Default
    public RootTreeDynamicEntity(UUID userId, UUID companyId, String name, UUID dynamicReference) {
        super(userId, companyId, name);
        this.dynamicReference = dynamicReference;
    }
}
