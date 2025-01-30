package org.gfinnovation.dealsafe.modules.tree.structure.domain.root;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;

import java.util.LinkedList;

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
public class RootTreeStatic extends RootTree<Object> {
    private PredefinedTypeEnum input_type;

    @Default
    public RootTreeStatic(
            String name,
            PredefinedTypeEnum type
    ) {
        super(name, NodeType.ROOT_STATIC);
        this.input_type = type;
    }

    public RootTreeStatic(
            String name,
            PredefinedTypeEnum type,
            LinkedList<NodeTree<?>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.input_type = type;
    }

    @Override
    public boolean traverse(Object inputData) {
        return false;
    }
}