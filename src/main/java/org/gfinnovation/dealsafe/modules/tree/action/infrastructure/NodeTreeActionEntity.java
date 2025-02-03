package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeActionEntity
 * @since 22/01/2025
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_ACTION)
@Table(
        name = "tree_node_action",
        indexes = {
                @Index(name = "idx_tree_node_action_id", columnList = "id")
        }
)
public class NodeTreeActionEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    ActionEnum actionType;
}