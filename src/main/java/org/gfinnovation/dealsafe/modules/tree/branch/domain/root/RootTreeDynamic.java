package org.gfinnovation.dealsafe.modules.tree.branch.domain.root;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;

import java.util.UUID;

/**
 * Maps Root attributes if the input of the validation tree is dynamic.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamic
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeDynamic extends RootTree {
    private UUID dynamicReference;

    @Default
    public RootTreeDynamic(String name, UUID dynamicReference) {
        super(name);
        this.dynamicReference = dynamicReference;
    }
}
