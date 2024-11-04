package org.gfinnovation.dealsafe.domains.tree.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * NodeTreeEntity is the base foundation where Dealsafe sits upon.
 * It follows the general struct of leafs on an ordinary data tree.
 * A node can be both a parent and a child, algo it can contain operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeEntity
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTreeEntity extends GenericBusinessEntity {
    @Setter
    private String name;

    @Setter
    private Integer sequence;

    private UUID parentId;

    private String parentType;

    @Setter
    private Set<NodeTreeEntity> children = new HashSet<>();

    @Setter
    private Set<ComparisonOperationEntity> operations = new HashSet<>();

    public NodeTreeEntity(UUID userId, UUID companyId, String name, Integer sequence) {
        super(userId, companyId);
        this.name = name;
        this.sequence = sequence;
    }

    @Default
    public NodeTreeEntity(UUID userId, UUID companyId, String name, Integer sequence, UUID parentId, String parentType) {
        super(userId, companyId);
        this.name = name;
        this.sequence = sequence;
        this.parentId = parentId;
        this.parentType = parentType;
    }

    public NodeTreeEntity addChild(NodeTreeEntity child) {
        this.children.add(child);
        child.setParent(this.getId(), "NODE");
        return this;
    }

    public NodeTreeEntity addOperation(ComparisonOperationEntity operation) {
        this.operations.add(operation);
        return this;
    }

    public boolean hasParent() {
        return parentId != null && (parentType.equals("NODE") || parentType.equals("ROOT"));
    }

    public void setParent(UUID parentId, String parentType) {
        this.parentId = parentId;
        this.parentType = parentType;
    }
}