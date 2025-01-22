package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.factory;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.factory.interfaces.NodeTreeFactory;
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
    public NodeTree produce(String name, Branch branch) throws FactoryException {
        try {
            return new NodeTree(name, branch);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }

    public NodeTreeAction produceAction(String name, Branch branch) throws FactoryException {
        try {
            return new NodeTreeAction(name, branch, ActionEnum.CREATE);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }
}