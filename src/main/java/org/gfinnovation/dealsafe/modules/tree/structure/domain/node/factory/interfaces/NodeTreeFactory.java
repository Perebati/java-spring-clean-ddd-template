package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces;

import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;

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
