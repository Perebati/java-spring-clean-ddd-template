package org.gfinnovation.dealsafe.domains.tree.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessEntity;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * It sets off what type of tree will be build using nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeEntity
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeEntity extends GenericBusinessEntity {
    private String name;
    private Set<NodeTreeEntity> nodes;

    @Default
    public RootTreeEntity(UUID userId, UUID companyId, String name) {
        super(userId, companyId);
        this.name = name;
        this.nodes = new HashSet<>();
    }

    public RootTreeEntity addNode(NodeTreeEntity entity) {
        this.nodes.add(entity);
        entity.setParent(this.getId(), "ROOT");
        return this;
    }
}