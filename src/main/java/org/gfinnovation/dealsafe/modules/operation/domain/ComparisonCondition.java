package org.gfinnovation.dealsafe.modules.operation.domain;

import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.logic.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.operation.domain.valueobjects.ComparisonContext;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonCondition
 * @since 14/01/2025
 */

@AllArgsConstructor
public class ComparisonCondition extends Condition {
    private ComparisonOperationEntity comparisonOperationEntity;
    private ComparisonContext comparisonContext;

    @Override
    public boolean execute() {
        try {
            String varLeft = comparisonContext.getVariable(comparisonOperationEntity.getJsonVariablePath());
            String varRight = comparisonContext.getVariable(comparisonOperationEntity.getExpectedVars().iterator().next());

            ComparisonTypeEnum comparisonTypeEnum = comparisonOperationEntity.getComparisonTypeEnum();
            ComparisonOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(varLeft, varRight);
        } catch (Exception e) {
            return false;
        }
    }
}
