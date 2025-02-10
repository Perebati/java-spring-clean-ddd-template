package org.gfinnovation.dealsafe.modules.tree.comparison.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query.ReadCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.ComparisonMultiOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.ContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.NotContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomList
 * @since v1.0 (06/02/2025)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonCustomList
        extends NodeTree<JsonNode> {
    private ComparisonCustomListEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private UUID customListId;

    @Default
    public ComparisonCustomList(
            ComparisonCustomListEnum comparisonTypeEnum,
            String jsonPath,
            UUID customListId,
            Node<?> parentNode
    ) {
        super(NodeType.CONDITIONAL_COMPARISON_CUSTOM, parentNode);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.customListId = customListId;
    }

    @Override
    public boolean traverse(JsonNode data) {
        try {
            String inputData = data.at(this.jsonVariablePath).asText();
            ComparisonMultiOperation comparisonOperation =
                    this.comparisonTypeEnum.createOperationInstance();

            List<String> cnpjs = ReadCustomList.execute(this.customListId);

            return comparisonOperation.doOperation(inputData, cnpjs);
        } catch (Exception e) {
            return false;
        }
    }

    @Getter
    public enum ComparisonCustomListEnum {
        CONTAINS(ContainsOperation.class),
        NOT_CONTAINS(NotContainsOperation.class);

        private final Class<? extends ComparisonMultiOperation> operationClass;

        ComparisonCustomListEnum(Class<? extends ComparisonMultiOperation> operationClass) {
            this.operationClass = operationClass;
        }

        public ComparisonMultiOperation createOperationInstance() throws Exception {
            return operationClass.getDeclaredConstructor().newInstance();
        }
    }
}
