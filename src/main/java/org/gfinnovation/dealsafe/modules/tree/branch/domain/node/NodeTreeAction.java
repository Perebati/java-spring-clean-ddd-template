package org.gfinnovation.dealsafe.modules.tree.branch.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;

import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeAction
 * @since 17/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeAction extends NodeTree {
    ActionEnum actionType;

    @Default
    public NodeTreeAction(
            String name,
            List<NodeTree> nodes,
            UUID parentId,
            ParentType parentType,
            ActionEnum actionType) {
        super(name, nodes, parentId, parentType);
        this.actionType = actionType;
        this.setNodeType(NodeType.NODE_ACTION);
    }

    public NodeTreeAction(
            String name,
            Branch parent,
            ActionEnum actionType) {
        super(name, parent);
        this.actionType = actionType;
        this.setNodeType(NodeType.NODE_ACTION);
    }

    @Override
    public boolean execute(String input) {
        System.out.println("Dentro de um Node de ação!");
        for(NodeTree node: this.getNodes()){
            node.execute(input);
        }
        return true;
    }

    public boolean traverse() {
        return true;
    }

    public enum SortType {
        NAME, ACTION_TYPE
    }

    public record Input(String value) {
    }
}
