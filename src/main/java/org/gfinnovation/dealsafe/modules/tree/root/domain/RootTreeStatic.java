package org.gfinnovation.dealsafe.modules.tree.root.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.LinkedList;

/**
 * Maps Root attributes if the input of the validation tree is predefined.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeStatic
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeStatic extends RootTree<NodeInput> {
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
            LinkedList<NodeTree<NodeInput>> nodes
    ) {
        super(name, NodeType.ROOT_STATIC, nodes);
        this.input_type = type;
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        for (NodeTree<NodeInput> node : getNodes()) {
            if (!node.traverse(inputData)) return false;
        }
        return true;
    }
}