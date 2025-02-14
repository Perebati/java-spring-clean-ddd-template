package org.gfinnovation.dealsafe.modules.tree.root.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.RootTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeDynamicEntity
 * @since v1.0 (30/11/2024)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_dynamic_root_x_root"))
@Table(
        name = "tree_root_dynamic",
        indexes = {
                @Index(name = "idx_tree_root_dynamic_id", columnList = "id")
        }
)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_DYNAMIC)
public class RootTreeDynamicEntity extends RootTreeEntity {
    @Column(name = "dynamic_input_id", nullable = false)
    private UUID dynamicInputId;
}
