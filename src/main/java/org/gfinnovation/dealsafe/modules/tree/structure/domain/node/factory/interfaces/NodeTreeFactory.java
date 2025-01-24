package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeFactory
 * @since 30/10/2024
 */

public interface NodeTreeFactory {
    NodeTreeBlock produce(String name, Node<?> nodeParent) throws FactoryException;

    NodeTreeAction produceAction(String name, Node<?> nodeTreeBlock) throws FactoryException;
}
