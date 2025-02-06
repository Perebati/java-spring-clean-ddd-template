package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_COMPARISON_MULTI)
@Table(name = "tree_root_node_condition_comparison_multi")
public class ComparisonMultiEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "comparisonType", nullable = false)
    private ComparisonMultiTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "_relation_mulitComp_x_vars",
            joinColumns = @JoinColumn(name = "comparison_id")
    )
    @Column(name = "expected_vars", columnDefinition = "TEXT", nullable = false)
    private List<String> expectedVars = new ArrayList<>();

    public enum ComparisonMultiTypeEnum {
        CONTAINS,
        NOT_CONTAINS
    }
}