package org.gfinnovation.dealsafe.modules.tree.node.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeTreeEntity;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlockEntity
 * @since 24/01/2025
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_BLOCK)
@Table(
        name = "tree_node_block"
)
public class NodeTreeBlockEntity extends NodeTreeEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "_relation_block_x_node",
            joinColumns = @JoinColumn(name = "parent_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "node_id", nullable = false)
    )
    @OrderColumn(name = "node_order")
    private List<NodeTreeEntity> nodes;
}
