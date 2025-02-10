package org.gfinnovation.dealsafe.modules.tree.action.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeAction
 * @since 17/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeAction extends NodeTree<NodeInput> {
    ActionEnum actionType;

    public NodeTreeAction(
            String name,
            Node<?> parent,
            ActionEnum actionType) {
        super(NodeType.NODE_ACTION, parent);
        this.actionType = actionType;
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        return true;
    }

    public enum SortType {
        NAME, ACTION_TYPE
    }
}