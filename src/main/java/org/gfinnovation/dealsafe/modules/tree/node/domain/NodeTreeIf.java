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
 * @class NodeTreeIf
 * @since 16/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class NodeTreeIf
        extends NodeTree<JsonNode> {
    private final List<NodeTree<JsonNode>> conditionalNodes = new ArrayList<>();
    private final List<NodeTree<JsonNode>> thenNodes = new ArrayList<>();
    private final List<NodeTree<JsonNode>> elseNodes = new ArrayList<>();

    @Default
    public NodeTreeIf(
            Node<?> parentNode
    ) {
        super(NodeType.NODE_IF, parentNode);
    }

    public void addConditionalNode(NodeTree<JsonNode> node) {
        conditionalNodes.add(node);
    }

    public void addThenNode(NodeTree<JsonNode> node) {
        thenNodes.add(node);
    }

    public void addElseNode(NodeTree<JsonNode> node) {
        elseNodes.add(node);
    }

    public enum SetNode {
        CONDITIONAL, THEN, ELSE
    }

    @Override
    public boolean traverse(JsonNode inputData) {
        for(NodeTree<JsonNode> node : getConditionalNodes()) {
            if(!node.traverse(inputData)){
                for (NodeTree<JsonNode> elseNode : elseNodes) {
                    if(!elseNode.traverse(inputData)){
                        return false;
                    }
                }
            }else{
                for (NodeTree<JsonNode> thenNode : thenNodes) {
                    if(!thenNode.traverse(inputData)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}