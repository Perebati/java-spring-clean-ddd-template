package org.gfinnovation.dealsafe.modules.tree.node.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeIf
 * @since 16/01/2025
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("NodeTreeIf")
@Schema(name = "NodeTreeIf", description = "Representa um nó do tipo IF na árvore de nós")
public class NodeTreeIf extends NodeTree<NodeInput> {

    @ArraySchema(schema = @Schema(
            oneOf = {
                    NodeTreeIf.class,
                    NodeTreeBlock.class,
                    ComparisonSingular.class,
                    ComparisonMulti.class,
                    ComparisonCustomList.class
            }
    ))
    private List<NodeTree<NodeInput>> conditionalNodes = new ArrayList<>();

    @ArraySchema(schema = @Schema(
            oneOf = {
                    NodeTreeIf.class,
                    NodeTreeBlock.class,
                    ComparisonSingular.class,
                    ComparisonMulti.class,
                    ComparisonCustomList.class
            }
    ))
    private List<NodeTree<NodeInput>> thenNodes = new ArrayList<>();

    @ArraySchema(schema = @Schema(
            oneOf = {
                    NodeTreeIf.class,
                    NodeTreeBlock.class,
                    ComparisonSingular.class,
                    ComparisonMulti.class,
                    ComparisonCustomList.class
            }
    ))
    private List<NodeTree<NodeInput>> elseNodes = new ArrayList<>();

    @Default
    public NodeTreeIf(
            Node<?> parentNode
    ) {
        super(NodeType.NODE_IF, parentNode);
    }

    @JsonCreator
    public NodeTreeIf(
            @JsonProperty("conditionalNodes") List<NodeTree<NodeInput>> conditionalNodes,
            @JsonProperty("thenNodes") List<NodeTree<NodeInput>> thenNodes,
            @JsonProperty("elseNodes") List<NodeTree<NodeInput>> elseNodes

    ) {
        super(NodeType.NODE_IF, null);
        this.conditionalNodes = (conditionalNodes != null) ? conditionalNodes : new ArrayList<>();
        this.thenNodes = (thenNodes != null) ? thenNodes : new ArrayList<>();
        this.elseNodes = (elseNodes != null) ? elseNodes : new ArrayList<>();
    }

    public void addConditionalNode(NodeTree<NodeInput> node) {
        conditionalNodes.add(node);
        node.setParent(this);
    }

    public void addThenNode(NodeTree<NodeInput> node) {
        thenNodes.add(node);
        node.setParent(this);
    }

    public void addElseNode(NodeTree<NodeInput> node) {
        elseNodes.add(node);
        node.setParent(this);
    }

    public void removeConditionalNode(UUID nodeId) {
        Optional<NodeTree<NodeInput>> nodeOpt = conditionalNodes.stream()
                .filter(n -> n.getId().equals(nodeId))
                .findFirst();
        nodeOpt.ifPresent(node -> {
            conditionalNodes.remove(node);
            node.setParent(null);
        });
    }

    public void removeThenNode(UUID nodeId) {
        Optional<NodeTree<NodeInput>> nodeOpt = thenNodes.stream()
                .filter(n -> n.getId().equals(nodeId))
                .findFirst();
        nodeOpt.ifPresent(node -> {
            thenNodes.remove(node);
            node.setParent(null);
        });
    }

    public void removeElseNode(UUID nodeId) {
        Optional<NodeTree<NodeInput>> nodeOpt = elseNodes.stream()
                .filter(n -> n.getId().equals(nodeId))
                .findFirst();
        nodeOpt.ifPresent(node -> {
            elseNodes.remove(node);
            node.setParent(null);
        });
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