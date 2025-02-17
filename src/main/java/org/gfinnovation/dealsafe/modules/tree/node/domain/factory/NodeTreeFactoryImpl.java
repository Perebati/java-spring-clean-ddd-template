package org.gfinnovation.dealsafe.modules.tree.node.domain.factory;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.springframework.stereotype.Component;

/**
 * Handles the creation and validation of Nodes.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeFactoryImpl
 * @since v1.0 (30/11/2024)
 */
@Component
class NodeTreeFactoryImpl implements NodeTreeFactory {

    /**
     * Validates business information and creates a NodeTree.
     *
     * @param name Name of the node.
     * @return NodeTree
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */
    public NodeTreeBlock produceBlock(String name, Node<?> nodeParent) throws SystemGlobalException {
        try {
            return new NodeTreeBlock(name, nodeParent);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a node.", e);
        }
    }

    public NodeTreeIf produceIf(Node<?> nodeParent) throws SystemGlobalException {
        try {
            return new NodeTreeIf(nodeParent);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a node.", e);
        }
    }
}