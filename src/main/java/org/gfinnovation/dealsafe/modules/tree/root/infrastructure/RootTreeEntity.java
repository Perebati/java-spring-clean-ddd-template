package org.gfinnovation.dealsafe.modules.tree.root.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeEntity
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_ROOT)
@Table(
        name = "tree_root",
        indexes = {
                @Index(name = "idx_tree_root_id", columnList = "id")
        }
)
public class RootTreeEntity extends NodeEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "_relation_root_x_node",
            joinColumns = @JoinColumn(name = "root_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    @OrderColumn(name = "node_order")
    private List<NodeTreeEntity> nodes;
}