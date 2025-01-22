package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.Condition;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.enums.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.valueobjects.ComparisonContext;

import java.util.Set;

/**
 * An comparison compares variables (:
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Comparison
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class Comparison extends Condition<ComparisonContext> {
    private ComparisonTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private Set<String> expectedVars;

    @Default
    public Comparison(
            ComparisonTypeEnum comparisonTypeEnum,
            String jsonPath,
            Set<String> variables
    ) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
    }

    public void addVariable(String variable) {
        this.expectedVars.add(variable);
    }

    // TODO: Rever este evaluate
    @Override
    public boolean evaluate(ComparisonContext data) {
        try {
            String varLeft = data.getVariable(this.getJsonVariablePath());
            String varRight = data.getVariable(this.getExpectedVars().iterator().next());

            ComparisonTypeEnum comparisonTypeEnum = this.getComparisonTypeEnum();
            ComparisonOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(varLeft, varRight);
        } catch (Exception e) {
            return false;
        }
    }
}