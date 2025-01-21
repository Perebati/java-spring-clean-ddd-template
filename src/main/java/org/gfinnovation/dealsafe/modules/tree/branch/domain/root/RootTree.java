package org.gfinnovation.dealsafe.modules.tree.branch.domain.root;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;

import java.util.List;

/**
 * It sets off what type of tree will be build using nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTree
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTree extends Branch {
    @Default
    public RootTree(String name, List<NodeTree> nodes) {
        super(name, nodes);
        this.setBranchType(BranchType.ROOT_COMMON);
    }

    public RootTree(String name) {
        super(name);
        this.setBranchType(BranchType.ROOT_COMMON);
    }
}