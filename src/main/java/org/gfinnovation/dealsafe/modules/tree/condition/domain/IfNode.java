package org.gfinnovation.dealsafe.modules.tree.condition.domain;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IfNode
 * @since 16/01/2025
 */

public class IfNode {
    private Condition condition;
    private List<NodeTree> thenNodes;
    private List<NodeTree> elseNodes;

    public IfNode(Condition condition) {
        this.condition = condition;
        this.thenNodes = new ArrayList<>();
        this.elseNodes = new ArrayList<>();
    }

    public void addThenNode(NodeTree node) {
        thenNodes.add(node);
    }

    public void addElseNode(NodeTree node) {
        elseNodes.add(node);
    }

    public void execute() throws Exception {
        if (condition.evaluate()) {
            for (NodeTree node : thenNodes) {
                node.execute();
            }
        } else {
            for (NodeTree node : elseNodes) {
                node.execute();
            }
        }
    }
}
