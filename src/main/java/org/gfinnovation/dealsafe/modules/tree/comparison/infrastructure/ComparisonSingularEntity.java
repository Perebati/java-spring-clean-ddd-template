package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeTreeEntity;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class OperationComparisonSchema
 * @since v1.0 (30/11/2024)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_COMPARISON_SINGULAR)
@Table(name = "tree_root_node_condition_comparison_singular")
public class ComparisonSingularEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "comparison_type", nullable = false)
    private ComparisonSingularEntityTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @Column(name = "expected_var", columnDefinition = "TEXT", nullable = false)
    private String expectedVar;

    public enum ComparisonSingularEntityTypeEnum {
        DIFFERENT,
        EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL
    }
}
