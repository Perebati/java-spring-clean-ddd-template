package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.Condition;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.ComparisonSingularOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.enums.ComparisonSingularTypeEnum;

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
public class ComparisonSingular extends Condition<JsonNode> {
    private ComparisonSingularTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private String expectedVar;

    @Default
    public ComparisonSingular(
            ComparisonSingularTypeEnum comparisonTypeEnum,
            String jsonPath,
            String variables
    ) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVar = variables;
    }

    @Override
    public boolean evaluate(JsonNode data) {
        try {
            String inputData = data.get(this.getJsonVariablePath()).asText();

            ComparisonSingularTypeEnum comparisonTypeEnum = this.getComparisonTypeEnum();
            ComparisonSingularOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(inputData, getExpectedVar());
        } catch (Exception e) {
            return false;
        }
    }
}