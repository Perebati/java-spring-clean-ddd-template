package org.gfinnovation.dealsafe.modules.tree.comparison.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.ComparisonMultiOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.ContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.NotContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMulti
 * @since v1.0 (06/02/2025)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonMulti
        extends NodeTree<NodeInput> {
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
    public boolean traverse(NodeInput data) {
        try {
            String inputData = data.getJsonNode().at(this.getJsonVariablePath()).asText();

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