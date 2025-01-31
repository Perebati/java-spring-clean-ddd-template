package org.gfinnovation.dealsafe.modules.tree.root.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicEntity
 * @since 30/10/2024
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
    @Column(name = "dynamic_input_id")
    private UUID dynamicInputId;
}
