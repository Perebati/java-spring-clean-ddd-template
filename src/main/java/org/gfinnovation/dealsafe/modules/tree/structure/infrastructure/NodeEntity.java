package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeEntity
 * @since 24/01/2025
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 3, discriminatorType = DiscriminatorType.STRING)
@Table(name = "tree")
public class NodeEntity extends GenericBusinessEntity {
    public final static String DISCRIMINATOR_ROOT = "RT";
    public final static String DISCRIMINATOR_NODE = "ND";
    public final static String DISCRIMINATOR_DYNAMIC = "DY";
    public final static String DISCRIMINATOR_STATIC = "ST";
    public final static String DISCRIMINATOR_ACTION = "AC";
    public final static String DISCRIMINATOR_CONDITION = "CD";
    public final static String DISCRIMINATOR_BLOCK = "BL";
    public final static String DISCRIMINATOR_IF = "IF";

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "node_type")
    private Node.NodeType nodeType;
}