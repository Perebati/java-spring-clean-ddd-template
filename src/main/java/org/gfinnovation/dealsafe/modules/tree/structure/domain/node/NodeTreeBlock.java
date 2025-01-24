package org.gfinnovation.dealsafe.modules.tree.structure.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBlock
 * @since 17/01/2025
 */
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NodeTreeBlock extends NodeTree<Object> {
    private List<NodeTree<?>> nodes = new ArrayList<>();

    @Default
    public NodeTreeBlock(
            String name
    ) {
        super(name, NodeType.NODE_BLOCK);
    }

    public NodeTreeBlock(
            String name,
            Node<?> parentNode
    ) {
        super(name, NodeType.NODE_BLOCK, parentNode);
    }

    public NodeTreeBlock(
            String name,
            Node<?> parentNode,
            List<NodeTree<?>> nodes
    ) {
        super(name, NodeType.NODE_BLOCK, parentNode);
        this.nodes = nodes;
    }

    public void addNode(NodeTree<?> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new RuntimeException("Error adding node to branch");
        }
    }

    @Override
    public boolean traverse(Object inputData) {
        return false;
    }
}