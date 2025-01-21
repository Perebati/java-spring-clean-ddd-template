package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.NodeTreeEntity;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BranchEntity
 * @since 17/01/2025
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 3, discriminatorType = DiscriminatorType.STRING)
@Table(name = "tree_branch")
public class BranchEntity extends GenericBusinessSchema {
    public final static String DISCRIMINATOR_DYNAMIC = "DY";
    public final static String DISCRIMINATOR_STATIC = "ST";
    public final static String DISCRIMINATOR_ROOT = "RT";
    public final static String DISCRIMINATOR_NODE = "ND";
    public final static String DISCRIMINATOR_ACTION = "AC";
    public final static String DISCRIMINATOR_CONDITION = "CD";

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_type")
    private Branch.BranchType branchType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "_relation_branch_x_node",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "node_id")
    )
    private List<NodeTreeEntity> nodes;
}