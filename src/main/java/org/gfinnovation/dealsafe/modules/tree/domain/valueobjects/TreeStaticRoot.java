package org.gfinnovation.dealsafe.modules.tree.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;

/**
 * Maps Root attributes if the input of the validation tree is predefined.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeStaticRoot
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TreeStaticRoot extends TreeRoot {
    private PredefinedTypeEnum type;

    @Default
    public TreeStaticRoot(String name, PredefinedTypeEnum type) {
        super(name);
        this.type = type;
    }
}
