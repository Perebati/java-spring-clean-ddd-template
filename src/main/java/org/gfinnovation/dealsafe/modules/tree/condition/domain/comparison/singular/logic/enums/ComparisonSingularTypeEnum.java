package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.enums;

import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.ComparisonSingularOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.types.*;

/**
 * This enum maps all possible comparisons in this system,
 * The logic behind operations are scripted inside the referenced class
 * in each enum.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @enum ComparisonTypeEnum
 * @since 30/10/2024
 */

public enum ComparisonSingularTypeEnum {
    DIFFERENT(IsDifferentOperation.class),
    EQUAL(IsEqualsOperation.class),
    LESSTHAN(IsLessThanOperation.class),
    LESSTHANOREQUAL(IsLessThanOrEqualOperation.class),
    GREATERTHAN(IsMoreThanOperation.class),
    GREATERTHANOREQUAL(IsMoreThanOrEqualOperation.class);

    private final Class<? extends ComparisonSingularOperation> operationClass;

    ComparisonSingularTypeEnum(Class<? extends ComparisonSingularOperation> operationClass) {
        this.operationClass = operationClass;
    }

    public Class<? extends ComparisonSingularOperation> getOperationClass() {
        return this.operationClass;
    }

    public ComparisonSingularOperation createOperationInstance() throws Exception {
        return operationClass.getDeclaredConstructor().newInstance();
    }
}
