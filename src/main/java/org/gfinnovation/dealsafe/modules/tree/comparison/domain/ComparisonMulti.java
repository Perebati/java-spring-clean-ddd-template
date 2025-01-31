package org.gfinnovation.dealsafe.modules.tree.comparison.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.ComparisonMultiOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.ContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.NotContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonMulti extends NodeTree<JsonNode> {
    private ComparisonMultiTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private List<String> expectedVars;

    @Default
    public ComparisonMulti(
            ComparisonMultiTypeEnum comparisonTypeEnum,
            String jsonPath,
            List<String> expectedVars,
            Node<?> parentNode
    ) {
        super(NodeType.CONDITIONAL_COMPARISON_MULTIPLE, parentNode);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = expectedVars;
    }

    @Override
    public boolean traverse(JsonNode data) {
        try {
            String inputData = data.get(this.getJsonVariablePath()).asText();

            ComparisonMultiTypeEnum comparisonTypeEnum = this.getComparisonTypeEnum();
            ComparisonMultiOperation comparisonOperation = comparisonTypeEnum.createOperationInstance();

            return comparisonOperation.doOperation(inputData, this.getExpectedVars());
        } catch (Exception e) {
            return false;
        }
    }

    @Getter
    public enum ComparisonMultiTypeEnum {
        CONTAINS(ContainsOperation.class),
        NOT_CONTAINS(NotContainsOperation.class);

        private final Class<? extends ComparisonMultiOperation> operationClass;

        ComparisonMultiTypeEnum(Class<? extends ComparisonMultiOperation> operationClass) {
            this.operationClass = operationClass;
        }

        public ComparisonMultiOperation createOperationInstance() throws Exception {
            return operationClass.getDeclaredConstructor().newInstance();
        }
    }
}