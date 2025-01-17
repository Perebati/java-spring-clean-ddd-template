package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.factory.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeFactory
 * @since 30/10/2024
 */

public interface NodeTreeFactory {
    NodeTree produce(String name, Branch branch) throws FactoryException;
}
