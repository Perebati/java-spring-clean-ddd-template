package org.gfinnovation.dealsafe.modules.tree.action.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeAction
 * @since 17/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeAction extends NodeTree<JsonNode> {
    ActionEnum actionType;

    public NodeTreeAction(
            String name,
            Node<?> parent,
            ActionEnum actionType) {
        super(NodeType.NODE_ACTION, parent);
        this.actionType = actionType;
    }

    @Override
    public boolean traverse(JsonNode inputData) {
        return true;
    }

    public enum SortType {
        NAME, ACTION_TYPE
    }
}