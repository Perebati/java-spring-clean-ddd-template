package org.gfinnovation.dealsafe.modules.tree.operation.domain;

import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.valueobjects.ComparisonContext;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonCondition
 * @since 14/01/2025
 */

@AllArgsConstructor
public class ComparisonCondition extends Condition {
    private ComparisonOperation comparisonOperation;
    private ComparisonContext comparisonContext;

    @Override
    public boolean execute() {
        try {
            String varLeft = comparisonContext.getVariable(comparisonOperation.getJsonVariablePath());
            String varRight = comparisonContext.getVariable(comparisonOperation.getExpectedVars().iterator().next());

            ComparisonTypeEnum comparisonTypeEnum = comparisonOperation.getComparisonTypeEnum();
            org.gfinnovation.dealsafe.modules.tree.operation.domain.comparison.logic.ComparisonOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(varLeft, varRight);
        } catch (Exception e) {
            return false;
        }
    }
}
