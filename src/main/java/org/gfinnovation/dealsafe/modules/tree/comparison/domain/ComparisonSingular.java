package org.gfinnovation.dealsafe.modules.tree.comparison.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.ComparisonSingularOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.types.*;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class Comparison
 * @since v1.0 (30/11/2024)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonSingular
        extends NodeTree<JsonNode> {
    private ComparisonSingularTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private String expectedVar;

    @Default
    public ComparisonSingular(
            ComparisonSingularTypeEnum comparisonTypeEnum,
            String jsonPath,
            String variables,
            Node<?> parentNode
    ) {
        super(NodeType.CONDITIONAL_COMPARISON_SINGULAR, parentNode);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVar = variables;
    }

    @Override
    public boolean traverse(JsonNode data) {
        try {
            String inputData = data.at(this.jsonVariablePath).asText();

            ComparisonSingularTypeEnum comparisonTypeEnum = this.getComparisonTypeEnum();
            ComparisonSingularOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(inputData, getExpectedVar());
        } catch (Exception e) {
            return false;
        }
    }

    @Getter
    public enum ComparisonSingularTypeEnum {
        DIFFERENT(IsDifferentOperation.class),
        EQUAL(IsEqualsOperation.class),
        LESS_THAN(IsLessThanOperation.class),
        LESS_THAN_OR_EQUAL(IsLessThanOrEqualOperation.class),
        GREATER_THAN(IsMoreThanOperation.class),
        GREATER_THAN_OR_EQUAL(IsMoreThanOrEqualOperation.class);

        private final Class<? extends ComparisonSingularOperation> operationClass;

        ComparisonSingularTypeEnum(Class<? extends ComparisonSingularOperation> operationClass) {
            this.operationClass = operationClass;
        }

        public ComparisonSingularOperation createOperationInstance() throws Exception {
            return operationClass.getDeclaredConstructor().newInstance();
        }
    }
}