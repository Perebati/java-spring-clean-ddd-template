package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.BranchEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeActionEntity
 * @since 22/01/2025
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(BranchEntity.DISCRIMINATOR_ACTION)
@Table(name = "tree_root_node_action")
public class NodeTreeActionEntity extends NodeTreeEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    ActionEnum actionType;
}
