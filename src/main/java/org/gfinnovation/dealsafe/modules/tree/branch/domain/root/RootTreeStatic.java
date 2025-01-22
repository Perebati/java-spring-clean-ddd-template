package org.gfinnovation.dealsafe.modules.tree.branch.domain.root;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;

import java.util.List;

/**
 * Maps Root attributes if the input of the validation tree is predefined.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStatic
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeStatic extends RootTree {
    private PredefinedTypeEnum input_type;

    @Default
    public RootTreeStatic(
            String name,
            List<NodeTree> nodes,
            PredefinedTypeEnum type
    ) {
        super(name, nodes);
        this.input_type = type;
        this.setNodeType(NodeType.ROOT_STATIC);
    }

    public RootTreeStatic(
            String name,
            PredefinedTypeEnum type
    ) {
        super(name);
        this.input_type = type;
        this.setNodeType(NodeType.ROOT_STATIC);
    }
}