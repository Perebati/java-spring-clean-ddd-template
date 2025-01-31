package org.gfinnovation.dealsafe.modules.tree.node.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

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
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_NODE)
@Table(
        name = "tree_node",
        indexes = {
                @Index(name = "idx_tree_node_id", columnList = "id")
        }
)
public class NodeTreeEntity extends NodeEntity {
    @Column(name = "parent_id")
    private UUID parentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "parent_type")
    private NodeTree.ParentType parentType;
}