package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_condition_comparison_multi")
public class ComparisonMultiEntity extends GenericBusinessEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ComparisonMultiTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "tree_root_node_condition_comparison_multi_variables",
            joinColumns = @JoinColumn(name = "comparison_id")
    )
    @Column(name = "expected_vars", columnDefinition = "TEXT", nullable = false)
    private List<String> expectedVars = new ArrayList<>();

    private enum ComparisonMultiTypeEnum {
        CONTAINS,
        NOT_CONTAINS;
    }
}