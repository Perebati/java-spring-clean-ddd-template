package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeEntity
 * @since v1.0 (30/11/2024)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(NodeEntity.DISCRIMINATOR_ROOT)
@Table(
        name = "tree_root"
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

    @Column(name = "version")
    private String version;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "_relation_root_x_history",
            joinColumns = @JoinColumn(name = "root_id"),
            inverseJoinColumns = @JoinColumn(name = "history_id")
    )
    @OrderColumn(name = "history_order")
    private List<RootTreeHistoryEntity> history = new ArrayList<>();
}