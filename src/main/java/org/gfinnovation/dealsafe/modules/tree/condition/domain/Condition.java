package org.gfinnovation.dealsafe.modules.tree.condition.domain;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Condition
 * @since 14/01/2025
 */

public abstract class Condition {
    public abstract boolean evaluate() throws Exception;
}
