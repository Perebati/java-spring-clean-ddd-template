package org.gfinnovation.dealsafe.modules.tree.action.domain;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionNode
 * @since 16/01/2025
 */

public class ActionNode {
    private final Action action;

    public ActionNode(Action action) {
        this.action = action;
    }

    public void execute() {
        action.execute();
    }
}
