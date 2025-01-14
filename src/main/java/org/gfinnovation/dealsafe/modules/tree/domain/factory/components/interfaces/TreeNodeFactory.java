package org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeFactory
 * @since 30/10/2024
 */

public interface TreeNodeFactory {
    TreeNode produce(String name, Integer sequence) throws FactoryException;
}
