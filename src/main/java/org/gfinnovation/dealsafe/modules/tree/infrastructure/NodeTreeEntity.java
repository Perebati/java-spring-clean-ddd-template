package org.gfinnovation.dealsafe.modules.tree.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.gfinnovation.dealsafe.modules.operation.infrastructure.ComparisonOperationSchema;

import java.util.Set;
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
@Table(name = "tree_root_node")
public class NodeTreeEntity extends GenericBusinessSchema {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "parent_type")
    private String parentType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "_relation_node_parent_x_node_child",
            joinColumns = @JoinColumn(name = "node_parent"),
            inverseJoinColumns = @JoinColumn(name = "node_child")
    )
    private Set<NodeTreeEntity> children;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "_relation_node_x_operation",
            joinColumns = @JoinColumn(name = "node_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private Set<ComparisonOperationSchema> operations;
}