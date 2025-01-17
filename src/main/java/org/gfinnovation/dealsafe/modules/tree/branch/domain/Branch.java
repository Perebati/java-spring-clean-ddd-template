package org.gfinnovation.dealsafe.modules.tree.branch.domain;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTree;

import java.util.LinkedList;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Branch
 * @since 17/01/2025
 */

public abstract class Branch extends GenericBusinessEntity {
    protected String name;
    protected LinkedList<NodeTree> nodes = new LinkedList<>();

    @Default
    public Branch(
            String name,
            LinkedList<NodeTree> nodes
    ) {
        this.name = name;
        this.nodes = nodes;
    }

    public Branch(String name) {
        this.name = name;
    }

    public enum ParentType {
        NODE, ROOT
    }

    public void addNode(NodeTree node) {
        this.nodes.add(node);
        node.setParentId(this.getId());
        if (this instanceof NodeTree) {
            node.setParentType(RootTree.ParentType.NODE);
        } else {
            node.setParentType(RootTree.ParentType.ROOT);
        }
    }
}
