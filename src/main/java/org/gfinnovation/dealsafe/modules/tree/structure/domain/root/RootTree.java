package org.gfinnovation.dealsafe.modules.tree.structure.domain.root;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
public class RootTree<T> extends Node<T> {
    private List<NodeTree<?>> nodes = new ArrayList<>();

    @Default
    public RootTree(
            String name,
            NodeType nodeType
    ) {
        setName(name);
        setNodeType(nodeType);
    }

    public RootTree(
            String name,
            NodeType nodeType,
            LinkedList<NodeTree<?>> nodes
    ) {
        setName(name);
        setNodeType(nodeType);
        this.nodes = nodes;
    }

    public void addNode(NodeTree<?> node) {
        try {
            this.nodes.add(node);
            node.setParent(this);
        } catch (Exception e) {
            throw new RuntimeException("Error adding node to branch");
        }
    }
}