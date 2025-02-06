package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListEntity
 * @since v1.0 (06/02/2025)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_COMPARISON_CUSTOM)
@Table(name = "tree_root_node_condition_comparison_custom")
public class ComparisonCustomListEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "comparison_Type", nullable = false)
    private ComparisonCustomTypeEnum comparisonTypeEnum;

    @Column(name = "json_variable_path", nullable = false)
    private String jsonVariablePath;

    @Column(name = "custom_list_id", nullable = false)
    private UUID customListId;

    public enum ComparisonCustomTypeEnum {
        CONTAINS,
        NOT_CONTAINS
    }
}
