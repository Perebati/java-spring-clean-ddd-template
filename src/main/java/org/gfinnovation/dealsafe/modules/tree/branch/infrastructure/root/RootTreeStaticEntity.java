package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.root;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.BranchEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticEntity
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(BranchEntity.DISCRIMINATOR_STATIC)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_static_root_x_root"))
@Table(
        name = "tree_root_node_static",
        indexes = {
                @Index(name = "idx_tree_root_static_id", columnList = "id")
        }
)
public class RootTreeStaticEntity extends RootTreeEntity {
    private PredefinedTypeEnum input_type;
}
