package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationComparisonSchema
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_condition_comparison_singular")
public class ComparisonSingularEntity extends GenericBusinessEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ComparisonSingularEntityTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @Column(name = "expected_var", columnDefinition = "TEXT", nullable = false)
    private String expectedVar;

    private enum ComparisonSingularEntityTypeEnum {
        DIFFERENT,
        EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL;
    }
}
