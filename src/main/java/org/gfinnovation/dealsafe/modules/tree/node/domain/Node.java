package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.domain.GenericBusinessClass;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;

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
public class Node<T extends NodeInput>
        extends GenericBusinessClass
        implements NodeTraversal<T> {
    private NodeType nodeType;

    public boolean traverse(T inputData) {
        return false;
    }

    public enum NodeType {
        NODE_BLOCK,
        NODE_ACTION,
        NODE_IF,
        NODE_CONDITION,
        ROOT_STATIC,
        ROOT_DYNAMIC,
        CONDITIONAL_COMPARISON_SINGULAR,
        CONDITIONAL_COMPARISON_MULTIPLE,
        CONDITIONAL_COMPARISON_CUSTOM
    }
}
