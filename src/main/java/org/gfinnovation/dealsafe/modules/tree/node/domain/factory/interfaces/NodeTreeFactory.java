package org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeFactory
 * @since v1.0 (30/11/2024)
 */
public interface NodeTreeFactory {
    NodeTreeBlock produceBlock(String name, Node<?> nodeParent) throws SystemGlobalException;

    NodeTreeIf produceIf(Node<?> nodeParent) throws SystemGlobalException;

}
