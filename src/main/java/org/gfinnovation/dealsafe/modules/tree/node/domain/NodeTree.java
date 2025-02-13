package org.gfinnovation.dealsafe.modules.tree.node.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.UUID;

/**
 * NodeTree is the base foundation where Dealsafe sits upon.
 * It follows the general struct of leafs on an ordinary data tree.
 * A node can be both a parent and a child, algo it can contain operations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTree
 * @since v1.0 (30/11/2024)
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
        @JsonSubTypes.Type(NodeTreeBlock.class),
        @JsonSubTypes.Type(NodeTreeIf.class),
        @JsonSubTypes.Type(ComparisonSingular.class),
        @JsonSubTypes.Type(ComparisonMulti.class),
        @JsonSubTypes.Type(ComparisonCustomList.class)
})
public class NodeTree<T extends NodeInput>
        extends Node<T> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID parentId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ParentType parentType;

    @Default
    public NodeTree(
            NodeType nodeType,
            Node<?> parentNode
    ) {
        setNodeType(nodeType);
        setParent(parentNode);
    }

    @JsonIgnore
    public void setParent(Node<?> parent) {
        if (parent == null) {
            this.parentId = null;
            this.parentType = null;
        } else {
            this.parentId = parent.getId();
            if (parent.getNodeType().equals(NodeType.ROOT_STATIC) ||
                    parent.getNodeType().equals(NodeType.ROOT_DYNAMIC)) {
                this.parentType = ParentType.ROOT;
            } else {
                this.parentType = ParentType.NODE;
            }
        }
    }

    @Override
    public boolean traverse(T inputData) {
        return false;
    }

    public enum ParentType {
        NODE, ROOT
    }
}