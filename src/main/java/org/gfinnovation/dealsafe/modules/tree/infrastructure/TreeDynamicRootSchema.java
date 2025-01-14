package org.gfinnovation.dealsafe.modules.tree.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeDynamicRootSchema
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(TreeRootSchema.DISCRIMINATOR_DYNAMIC)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_dynamic_root_x_root"))
@Table(name = "tree_root_node_dynamic")
public class TreeDynamicRootSchema extends TreeRootSchema {
    private UUID dynamicReference;
}
