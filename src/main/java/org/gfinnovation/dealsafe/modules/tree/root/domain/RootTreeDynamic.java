package org.gfinnovation.dealsafe.modules.tree.root.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

import java.util.LinkedList;
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
public class RootTreeDynamic extends RootTree<Object> {
    private UUID dynamicInputId;

    @Default
    public RootTreeDynamic(
            String name,
            UUID dynamicInputId
    ) {
        super(name, NodeType.ROOT_DYNAMIC);
        this.dynamicInputId = dynamicInputId;
    }

    public RootTreeDynamic(
            String name,
            UUID dynamicInputId,
            LinkedList<NodeTree<?>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.dynamicInputId = dynamicInputId;
    }

    @Override
    public boolean traverse(Object inputData) {
        return false;
    }
}