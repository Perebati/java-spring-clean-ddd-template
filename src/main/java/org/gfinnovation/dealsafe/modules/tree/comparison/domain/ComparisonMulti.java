package org.gfinnovation.dealsafe.modules.tree.comparison.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonTypeName("ComparisonMulti")
@Schema(name = "ComparisonMulti", description = "Representa um nó de comparação múltipla na árvore de nós")
public class ComparisonMulti extends NodeTree<NodeInput> {
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

    @JsonCreator
    public ComparisonMulti(
            @JsonProperty("comparisonTypeEnum") ComparisonMultiTypeEnum comparisonTypeEnum,
            @JsonProperty("jsonVariablePath") String jsonVariablePath,
            @JsonProperty("expectedVars") List<String> expectedVars

    ) {
        super(NodeType.CONDITIONAL_COMPARISON_MULTIPLE, null);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonVariablePath;
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