package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;

import java.util.UUID;

/**
 * NodeTree is the base foundation where Dealsafe sits upon.
 * It follows the general struct of leafs on an ordinary data tree.
 * A node can be both a parent and a child, algo it can contain operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTree
 * @since 30/10/2024
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class NodeTree<T> extends Node<T> {
    private UUID parentId;
    private ParentType parentType;

    @Default
    public NodeTree(
            NodeType nodeType,
            Node<?> parentNode
    ) {
        setNodeType(nodeType);
        this.parentId = parentNode.getId();
        setParent(parentNode);
    }

    public void setParent(Node<?> parent) {
        this.parentId = parent.getId();
        if (parent instanceof RootTree)
            this.parentType = ParentType.ROOT;
        else
            this.parentType = ParentType.NODE;
    }

    @Override
    public boolean traverse(T inputData) {
        return false;
    }

    public enum SortField {
        NAME
    }

    public enum ParentType {
        NODE, ROOT
    }
}