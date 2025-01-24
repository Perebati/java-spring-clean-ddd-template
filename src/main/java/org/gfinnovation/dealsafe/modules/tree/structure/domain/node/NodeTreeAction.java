package org.gfinnovation.dealsafe.modules.tree.structure.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeAction
 * @since 17/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeAction extends NodeTree<Object> {
    ActionEnum actionType;

    @Default
    public NodeTreeAction(
            String name,
            ActionEnum actionType
    ) {
        super(name, NodeType.NODE_ACTION);
        this.actionType = actionType;
    }

    public NodeTreeAction(
            String name,
            Node<?> parent,
            ActionEnum actionType) {
        super(name, NodeType.NODE_ACTION, parent);
        this.actionType = actionType;
    }

    @Override
    public boolean traverse(Object inputData) {
        return false;
    }

    public enum SortType {
        NAME, ACTION_TYPE
    }

}
