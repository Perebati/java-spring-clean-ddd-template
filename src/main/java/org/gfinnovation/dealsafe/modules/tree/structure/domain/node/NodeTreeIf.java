package org.gfinnovation.dealsafe.modules.tree.structure.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.Condition;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

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
    private final List<Condition<?>> conditionInterfaces = new ArrayList<>();
    private final List<NodeTree<?>> thenNodes = new ArrayList<>();
    private final List<NodeTree<?>> elseNodes = new ArrayList<>();

    @Default
    public NodeTreeIf(
            String name
    ) {
        super(name, NodeType.NODE_IF);
    }

    public NodeTreeIf(
            String name,
            Node<?> parentNode
    ) {
        super(name, NodeType.NODE_IF, parentNode);
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
}