package org.gfinnovation.dealsafe.modules.tree.branch.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;

import java.util.List;
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
public class NodeTree extends Branch implements NodeTreeInterface{
    private UUID parentId;
    private ParentType parentType;

    @Default
    public NodeTree(
            String name,
            List<NodeTree> nodes,
            UUID parentId,
            ParentType parentType
    ) {
        super(name, nodes);
        this.parentId = parentId;
        this.parentType = parentType;
        this.setBranchType(BranchType.NODE_COMMON);
    }

    public NodeTree(
            String name,
            Branch parent
    ) {
        super(name);
        setParent(parent);
        this.setBranchType(BranchType.NODE_COMMON);
    }

    public void setParent(Branch parent){
        this.parentId = parent.getId();
        if (parent instanceof NodeTree) {
            this.parentType = ParentType.NODE;
        } else {
            this.parentType = ParentType.ROOT;
        }
    }

    public enum SortField {
        NAME
    }

    @Override
    public boolean traverse() {
        return false;
    }
}