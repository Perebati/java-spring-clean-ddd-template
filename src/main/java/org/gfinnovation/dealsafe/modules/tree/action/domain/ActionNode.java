package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe.modules.tree.action.domain.Action;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionNode
 * @since 16/01/2025
 */

public class ActionNode  {
    private Action action;

    public ActionNode(Action action) {
        this.action = action;
    }

    public void execute() {
        action.execute();
    }
}
