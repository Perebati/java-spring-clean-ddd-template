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
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query.ReadCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.ComparisonMultiOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.ContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types.NotContainsOperation;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.utils.annotations.Default;

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
@JsonTypeName("ComparisonCustomList")
@Schema(name = "ComparisonCustomList", description = "Representa um nó de comparação customizada na árvore de nós")
public class ComparisonCustomList extends NodeTree<NodeInput> {
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

    @JsonCreator
    public ComparisonCustomList(
            @JsonProperty("comparisonTypeEnum") ComparisonCustomListEnum comparisonTypeEnum,
            @JsonProperty("jsonVariablePath") String jsonVariablePath,
            @JsonProperty("customListId") UUID customListId

    ) {
        super(NodeType.CONDITIONAL_COMPARISON_CUSTOM, null);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonVariablePath;
        this.customListId = customListId;
    }

    @Override
    public boolean traverse(NodeInput data) {
        try {
            String inputData = data.getJsonNode().at(this.jsonVariablePath).asText();
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
