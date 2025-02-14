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
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
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
@JsonTypeName("NodeTreeBlock")
@Schema(name = "NodeTreeBlock", description = "Representa um nó do tipo BLOCK na árvore de nós")
public class NodeTreeBlock
        extends NodeTree<NodeInput> {
    private String name;

    @ArraySchema(schema = @Schema(
            oneOf = {
                    NodeTreeBlock.class,
                    NodeTreeIf.class,
                    ComparisonSingular.class,
                    ComparisonMulti.class,
                    ComparisonCustomList.class
            }
    ))
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

    @JsonCreator
    public NodeTreeBlock(
            @JsonProperty("name") String name,
            @JsonProperty("nodes") List<NodeTree<NodeInput>> nodes
    ) {
        super(NodeType.NODE_BLOCK, null);
        this.name = name;
        this.nodes = (nodes != null) ? nodes : new ArrayList<>();
    }

    public void addNode(NodeTree<NodeInput> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new DomainException("Error adding node to parent");
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