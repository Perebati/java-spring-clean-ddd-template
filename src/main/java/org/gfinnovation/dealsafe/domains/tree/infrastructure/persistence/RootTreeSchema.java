package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessSchema;

import java.util.Set;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeSchema
 * @since 30/10/2024
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 3, discriminatorType = DiscriminatorType.STRING)
@Table(name = "tree_root")
public class RootTreeSchema extends GenericBusinessSchema {

    protected final static String DISCRIMINATOR_DYNAMIC = "DY";
    protected final static String DISCRIMINATOR_STATIC = "ST";

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "_relation_root_x_node",
            joinColumns = @JoinColumn(name = "root_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    private Set<NodeTreeSchema> nodes;
}
