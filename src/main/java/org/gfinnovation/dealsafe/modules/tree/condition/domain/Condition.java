package org.gfinnovation.dealsafe.modules.tree.condition.domain;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ConditionInterface
 * @since 16/01/2025
 */

public abstract class Condition<T> extends GenericBusinessClass {
    public abstract boolean evaluate(T data);
}
