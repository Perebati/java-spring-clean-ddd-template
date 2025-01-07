package org.gfinnovation.dealsafe.modules.operation.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationComparisonSchema
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_operation_comparison")
public class ComparisonOperationSchema extends GenericBusinessSchema {

    @Column(name = "type", nullable = false)
    private ComparisonTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "tree_root_node_operation_variables",
            joinColumns = @JoinColumn(name = "operation_id")
    )
    @Column(name = "variable", columnDefinition = "TEXT", nullable = false)
    private Set<String> expectedVars = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "_relation_operations_x_action",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private Set<ActionOperationSchema> actions = new HashSet<>();
}
