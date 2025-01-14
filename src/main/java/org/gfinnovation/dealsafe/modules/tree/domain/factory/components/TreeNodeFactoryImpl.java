package org.gfinnovation.dealsafe.modules.tree.domain.factory.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces.TreeNodeFactory;
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
class TreeNodeFactoryImpl implements TreeNodeFactory {

    /**
     * Validates business information and creates a TreeNode.
     *
     * @param name     Name of the node.
     * @param sequence Sequence position of given node.
     * @return TreeNode
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public TreeNode produce(String name, Integer sequence) throws FactoryException {
        try {
            return new TreeNode(name, sequence);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }
}
