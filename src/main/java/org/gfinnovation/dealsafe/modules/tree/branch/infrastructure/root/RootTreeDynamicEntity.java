package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.root;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.BranchEntity;

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
@DiscriminatorValue(BranchEntity.DISCRIMINATOR_DYNAMIC)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_dynamic_root_x_root"))
@Table(
        name = "tree_root_node_dynamic",
        indexes = {
                @Index(name = "idx_tree_root_dynamic_id", columnList = "id")
        }
)
public class RootTreeDynamicEntity extends RootTreeEntity {
    private UUID dynamicReference;
}
