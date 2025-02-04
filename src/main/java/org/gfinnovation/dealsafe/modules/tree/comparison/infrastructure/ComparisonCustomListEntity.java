package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;

import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tree_root_node_condition_comparison_custom")
public class ComparisonCustomListEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "comparisonType", nullable = false)
    private ComparisonCustomListEntity.ComparisonCustomTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @Column(name = "custom_list_id", nullable = false)
    private UUID customListId;

    public enum ComparisonCustomTypeEnum {
        CONTAINS,
        NOT_CONTAINS
    }
}
