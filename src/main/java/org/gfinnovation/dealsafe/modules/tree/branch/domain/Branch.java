package org.gfinnovation.dealsafe.modules.tree.branch.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Branch
 * @since 17/01/2025
 */

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Branch extends GenericBusinessEntity {
    private String name;
    private List<NodeTree> nodes;
    private BranchType branchType;

    @Default
    public Branch(
            String name,
            List<NodeTree> nodes
    ) {
        this.name = name;
        this.nodes = nodes;
        this.setBranchType(BranchType.BRANCH);
    }

    public Branch(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.setBranchType(BranchType.BRANCH);
    }

    public enum ParentType {
        NODE, ROOT
    }

    public void addNode(NodeTree node) {
        try {
            this.nodes.add(node);
            node.setParentId(this.getId());
            if (this instanceof NodeTree) {
                node.setParentType(RootTree.ParentType.NODE);
            } else {
                node.setParentType(RootTree.ParentType.ROOT);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error adding node to branch");
        }
    }

    public enum BranchType {
        BRANCH,
        NODE_COMMON,
        NODE_ACTION,
        NODE_CONDITION,
        ROOT_COMMON,
        ROOT_STATIC,
        ROOT_DYNAMIC
    }
}