package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.NodeEntity;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_IF)
@Table(
        name = "tree_node_if",
        indexes = {
                @Index(name = "idx_tree_node_if_id", columnList = "id")
        }
)
public class NodeTreeIfEntity extends NodeTreeEntity {
    @OneToMany
    @JoinTable(
            name = "_relation_if_x_cond",
            joinColumns = @JoinColumn(name = "if_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    @OrderColumn(name = "condition_order")
    private List<NodeTreeEntity> conditionalNodes;

    @OneToMany
    @JoinTable(
            name = "_relation_if_x_then",
            joinColumns = @JoinColumn(name = "if_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    @OrderColumn(name = "then_order")
    private List<NodeTreeEntity> thenNodes;

    @OneToMany
    @JoinTable(
            name = "_relation_if_x_else",
            joinColumns = @JoinColumn(name = "if_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    @OrderColumn(name = "else_order")
    private List<NodeTreeEntity> elseNodes;
}
