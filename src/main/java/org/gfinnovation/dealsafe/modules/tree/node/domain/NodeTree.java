package org.gfinnovation.dealsafe.modules.tree.node.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ComparisonOperation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * NodeTree is the base foundation where Dealsafe sits upon.
 * It follows the general struct of leafs on an ordinary data tree.
 * A node can be both a parent and a child, algo it can contain operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTree
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class NodeTree extends RootTree {
    @Setter
    private Integer sequence;

    private UUID parentId;

    private String parentType;

    @Setter
    private Set<ComparisonOperation> operations = new HashSet<>();

    public NodeTree(String name, Integer sequence) {
        super(name);
        this.sequence = sequence;
    }

    @Default
    public NodeTree(String name, Integer sequence, UUID parentId, String parentType) {
        super(name);
        this.sequence = sequence;
        this.parentId = parentId;
        this.parentType = parentType;
    }

    public NodeTree addChild(NodeTree child) {
        this.getNodes().add(child);
        child.setParent(this.getId(), "NODE");
        return this;
    }

    public NodeTree addOperation(ComparisonOperation operation) {
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

    public enum SortField {
        NAME
    }
}