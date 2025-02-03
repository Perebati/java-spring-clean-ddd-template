package org.gfinnovation.dealsafe.modules.tree.node.domain;

import com.fasterxml.jackson.databind.JsonNode;
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
 * @class NodeTreeBlock
 * @since 17/01/2025
 */
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NodeTreeBlock
        extends NodeTree<JsonNode> {
    private String name;
    private List<NodeTree<JsonNode>> nodes = new ArrayList<>();

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
            List<NodeTree<JsonNode>> nodes
    ) {
        super(NodeType.NODE_BLOCK, parentNode);
        this.name = name;
        this.nodes = nodes;
    }

    public void addNode(NodeTree<JsonNode> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new RuntimeException("Error adding node to branch");
        }
    }
    @Override
    public boolean traverse(JsonNode inputData) {
        for(NodeTree<JsonNode> node : getNodes()) {
            if(!node.traverse(inputData)) return false;
        }
        return true;
    }
}