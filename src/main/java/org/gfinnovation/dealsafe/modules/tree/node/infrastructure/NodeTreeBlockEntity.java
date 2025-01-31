package org.gfinnovation.dealsafe.modules.tree.node.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBlockEntity
 * @since 24/01/2025
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_BLOCK)
@Table(
        name = "tree_node_block",
        indexes = {
                @Index(name = "idx_tree_node_block_id", columnList = "id")
        }
)
public class NodeTreeBlockEntity
        extends NodeTreeEntity {
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinTable(
            name = "_relation_node_x_node",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    @OrderColumn(name = "node_order")
    private List<NodeTreeEntity> nodes;
}
