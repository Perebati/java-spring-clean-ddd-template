package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.BranchEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeEntity
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(BranchEntity.DISCRIMINATOR_NODE)
@Table(name = "tree_root_node")
public class NodeTreeEntity extends BranchEntity {
    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "parent_type")
    private String parentType;
}