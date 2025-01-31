package org.gfinnovation.dealsafe.modules.tree.root.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticEntity
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_static_root_x_root"))
@Table(
        name = "tree_root_static",
        indexes = {
                @Index(name = "idx_tree_root_static_id", columnList = "id")
        }
)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_STATIC)
public class RootTreeStaticEntity extends RootTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "input_type")
    private PredefinedTypeEnum input_type;
}
