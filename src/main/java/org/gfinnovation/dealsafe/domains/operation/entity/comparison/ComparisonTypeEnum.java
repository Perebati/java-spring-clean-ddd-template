package org.gfinnovation.dealsafe.domains.operation.entity.comparison;

import org.gfinnovation.dealsafe.domains.operation.entity.comparison.logic.ComparisonOperation;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.types.*;

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

public enum ComparisonTypeEnum {
    DIFFERENT(IsDifferentOperation.class),
    EQUAL(IsEqualsOperation.class),
    LESSTHAN(IsLessThanOperation.class),
    LESSTHANOREQUAL(IsLessThanOrEqualOperation.class),
    GREATERTHAN(IsMoreThanOperation.class),
    GREATERTHANOREQUAL(IsMoreThanOrEqualOperation.class);

    private final Class<? extends ComparisonOperation> operationClass;

    ComparisonTypeEnum(Class<? extends ComparisonOperation> operationClass) {
        this.operationClass = operationClass;
    }

    public Class<? extends ComparisonOperation> getOperationClass() {
        return this.operationClass;
    }

    public ComparisonOperation createOperationInstance() throws Exception {
        return operationClass.getDeclaredConstructor().newInstance();
    }
}
