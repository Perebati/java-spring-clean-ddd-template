package org.gfinnovation.dealsafe.modules.tree.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;

import java.util.HashSet;
import java.util.Set;

/**
 * It sets off what type of tree will be build using nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTree
 * @since 30/10/2024
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTree extends GenericBusinessEntity {
    private String name;
    private Set<NodeTree> nodes;

    @Default
    public RootTree(String name) {
        this.name = name;
        this.nodes = new HashSet<>();
    }

    public void addNode(NodeTree entity) {
        this.nodes.add(entity);
        entity.setParent(this.getId(), "ROOT");
    }
}