package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlock
 * @since 17/01/2025
 */
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NodeTreeBlock
        extends NodeTree<NodeInput> {
    private String name;
    private List<NodeTree<NodeInput>> nodes = new ArrayList<>();

    @Default
    public NodeTreeBlock(
            String name,
            Node<?> parentNode
    ) {
        super(NodeType.NODE_BLOCK, parentNode);
        this.name = name;
    }

    public NodeTreeBlock(
            String name,
            Node<?> parentNode,
            List<NodeTree<NodeInput>> nodes
    ) {
        super(NodeType.NODE_BLOCK, parentNode);
        this.name = name;
        this.nodes = nodes;
    }

    public void addNode(NodeTree<NodeInput> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new DomainException("Error adding node to branch");
        }
    }

    @Override
    public boolean traverse(NodeInput inputData) {
        for (NodeTree<NodeInput> node : getNodes()) {
            if (!node.traverse(inputData)) return false;
        }
        return true;
    }
}