package org.gfinnovation.dealsafe.modules.tree.root.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.RootTreeEntity;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeStaticEntity
 * @since v1.0 (30/11/2024)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_static_root_x_root"))
@Table(
        name = "tree_root_static"
)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_STATIC)
public class RootTreeStaticEntity extends RootTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "input_type", nullable = false)
    private PredefinedTypeEnum input_type;
}
