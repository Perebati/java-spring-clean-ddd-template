package org.gfinnovation.dealsafe.modules.tree.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticSchema
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(RootTreeSchema.DISCRIMINATOR_STATIC)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_static_root_x_root"))
@Table(name = "tree_root_node_static")
@AttributeOverride(name = "type", column = @Column(name = "static_type"))
public class RootTreeStaticSchema extends RootTreeSchema {
    private PredefinedTypeEnum type;
}
