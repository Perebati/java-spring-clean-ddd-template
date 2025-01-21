package org.gfinnovation.dealsafe.modules.tree.branch.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.Condition;

import java.util.LinkedList;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeCondition
 * @since 16/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeCondition extends NodeTree {
    private final LinkedList<Condition<?>> conditionInterfaces;
    private final LinkedList<NodeTree> thenNodes = new LinkedList<>();
    private final LinkedList<NodeTree> elseNodes = new LinkedList<>();

    @Default
    public NodeTreeCondition(
            String name,
            Branch parent,
            LinkedList<Condition<?>> conditionInterfaces
    ) {
        super(name, parent);
        this.conditionInterfaces = conditionInterfaces;
        this.setBranchType(BranchType.NODE_CONDITION);
    }

    public void addThenNode(NodeTree node) {
        thenNodes.add(node);
    }

    public void addElseNode(NodeTree node) {
        elseNodes.add(node);
    }
}