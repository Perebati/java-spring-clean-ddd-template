package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeIf
 * @since 16/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class NodeTreeIf extends NodeTree<Object> {
    private final List<NodeTree<?>> conditionalNodes = new ArrayList<>();
    private final List<NodeTree<?>> thenNodes = new ArrayList<>();
    private final List<NodeTree<?>> elseNodes = new ArrayList<>();

    @Default
    public NodeTreeIf(
            Node<?> parentNode
    ) {
        super(NodeType.NODE_IF, parentNode);
    }

    public void addConditionalNode(NodeTree<?> node) {
        conditionalNodes.add(node);
    }

    public void addThenNode(NodeTree<?> node) {
        thenNodes.add(node);
    }

    public void addElseNode(NodeTree<?> node) {
        elseNodes.add(node);
    }

    @Override
    public boolean traverse(Object inputData) {
        return false;
    }

    public enum SetNode {
        CONDITIONAL, THEN, ELSE
    }
}