package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.springframework.stereotype.Component;

/**
 * Handles the creation and validation of Nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeFactoryImpl
 * @since 30/10/2024
 */

@Component
class NodeTreeFactoryImpl implements NodeTreeFactory {

    /**
     * Validates business information and creates a NodeTree.
     *
     * @param name Name of the node.
     * @return NodeTree
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public NodeTreeBlock produceBlock(String name, Node<?> nodeParent) throws FactoryException {
        try {
            return new NodeTreeBlock(name, nodeParent);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }

    public NodeTreeIf produceIf(Node<?> nodeParent) throws FactoryException {
        try {
            return new NodeTreeIf(nodeParent);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }
}