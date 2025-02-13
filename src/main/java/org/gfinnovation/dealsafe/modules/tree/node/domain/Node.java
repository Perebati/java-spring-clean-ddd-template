package org.gfinnovation.dealsafe.modules.tree.node.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class Node
 * @since 24/01/2025
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
        @JsonSubTypes.Type(RootTreeStatic.class),
        @JsonSubTypes.Type(RootTreeDynamic.class),
        @JsonSubTypes.Type(NodeTreeBlock.class),
        @JsonSubTypes.Type(NodeTreeIf.class),
        @JsonSubTypes.Type(ComparisonSingular.class),
        @JsonSubTypes.Type(ComparisonMulti.class),
        @JsonSubTypes.Type(ComparisonCustomList.class)
})
public class Node<T extends NodeInput>
        extends GenericBusinessClass
        implements NodeTraversal<T> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private NodeType nodeType;

    public boolean traverse(T inputData) {
        return false;
    }

    public enum NodeType {
        ROOT_STATIC,
        ROOT_DYNAMIC,
        NODE_BLOCK,
        NODE_ACTION,
        NODE_IF,
        CONDITIONAL_COMPARISON_SINGULAR,
        CONDITIONAL_COMPARISON_MULTIPLE,
        CONDITIONAL_COMPARISON_CUSTOM
    }
}