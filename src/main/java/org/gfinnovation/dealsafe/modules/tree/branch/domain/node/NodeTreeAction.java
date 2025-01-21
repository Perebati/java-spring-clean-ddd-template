package org.gfinnovation.dealsafe.modules.tree.branch.domain.node;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeAction
 * @since 17/01/2025
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeAction extends NodeTree {
    ActionEnum actionType;

    @Default
    public NodeTreeAction(
            String name,
            Branch parent,
            ActionEnum actionType) {
        super(name, parent);
        this.actionType = actionType;
        this.setBranchType(BranchType.NODE_ACTION);
    }

    public enum SortType{
        NAME, ACTION_TYPE
    }

    @Override
    public boolean traverse() {
        return true;
    }
}
