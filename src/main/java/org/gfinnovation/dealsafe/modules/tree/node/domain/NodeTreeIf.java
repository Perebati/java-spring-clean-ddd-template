package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeIf
 * @since 16/01/2025
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class NodeTreeIf
        extends NodeTree<NodeInput> {
    private final List<NodeTree<NodeInput>> conditionalNodes = new ArrayList<>();
    private final List<NodeTree<NodeInput>> thenNodes = new ArrayList<>();
    private final List<NodeTree<NodeInput>> elseNodes = new ArrayList<>();

    @Default
    public NodeTreeIf(
            Node<?> parentNode
    ) {
        super(NodeType.NODE_IF, parentNode);
    }

    public void addConditionalNode(NodeTree<NodeInput> node) {
        conditionalNodes.add(node);
    }

    public void addThenNode(NodeTree<NodeInput> node) {
        thenNodes.add(node);
    }

    public void addElseNode(NodeTree<NodeInput> node) {
        elseNodes.add(node);
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        boolean flag = true;
        for (NodeTree<NodeInput> node : getConditionalNodes()) {
            if (!node.traverse(inputData)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (NodeTree<NodeInput> thenNode : thenNodes) {
                if (!thenNode.traverse(inputData)) {
                    return false;
                }
            }
        } else {
            for (NodeTree<NodeInput> elseNode : elseNodes) {
                if (!elseNode.traverse(inputData)) {
                    return false;
                }
            }
        }
        return true;
    }

    public enum SetNode {
        CONDITIONAL, THEN, ELSE
    }
}