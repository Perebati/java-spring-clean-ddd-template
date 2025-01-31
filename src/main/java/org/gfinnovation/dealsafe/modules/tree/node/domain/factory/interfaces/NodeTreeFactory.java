package org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeFactory
 * @since 30/10/2024
 */

public interface NodeTreeFactory {
    NodeTreeBlock produceBlock(String name, Node<?> nodeParent) throws FactoryException;

    NodeTreeIf produceIf(Node<?> nodeParent) throws FactoryException;

}
