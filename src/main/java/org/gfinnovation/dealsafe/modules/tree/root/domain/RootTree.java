package org.gfinnovation.dealsafe.modules.tree.root.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * It sets off what type of tree will be build using nodes.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTree
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
        @JsonSubTypes.Type(RootTreeStatic.class),
        @JsonSubTypes.Type(RootTreeDynamic.class)
})
public class RootTree<T extends NodeInput> extends Node<T> {
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
    private List<NodeTree<T>> nodes = new ArrayList<>();

    @Default
    public RootTree(
            String name,
            NodeType nodeType
    ) {
        setName(name);
        setNodeType(nodeType);
    }

    public RootTree(
            String name,
            NodeType nodeType,
            LinkedList<NodeTree<T>> nodes
    ) {
        setName(name);
        setNodeType(nodeType);
        this.nodes = nodes;
    }

    public void addNode(NodeTree<T> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new RuntimeException("Error adding node to branch");
        }
    }

    @Override
    public boolean traverse(T inputData) {
        return false;
    }
}