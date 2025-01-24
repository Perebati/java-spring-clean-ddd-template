package org.gfinnovation.dealsafe.modules.tree.structure.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Node
 * @since 24/01/2025
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Node<T> extends GenericBusinessClass implements NodeTraversal<T> {
    private String name;
    private NodeType nodeType;

    @Override
    public boolean traverse(T inputData) {
        return false;
    }

    public enum NodeType {
        NODE_BLOCK,
        NODE_ACTION,
        NODE_IF,
        NODE_CONDITION,
        ROOT_STATIC,
        ROOT_DYNAMIC
    }
}
