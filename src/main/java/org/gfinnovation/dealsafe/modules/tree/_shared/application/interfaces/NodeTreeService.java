package org.gfinnovation.dealsafe.modules.tree._shared.application.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeService
 * @since v1.0 (10/02/2025)
 */
public interface NodeTreeService<T extends NodeTree<NodeInput>>
        extends GenericService<NodeTree<NodeInput>> {
    T createNode(T newNode,
                 Node<?> parent,
                 NodeTreeIf.SetNode nodeSet) throws SystemGlobalException;
}
