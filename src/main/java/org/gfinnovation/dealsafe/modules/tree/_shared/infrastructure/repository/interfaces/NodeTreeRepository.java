package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeRepository
 * @since 24/01/2025
 */
public interface NodeTreeRepository<T extends NodeTree<NodeInput>>
        extends GenericBusinessRepository<NodeTree<NodeInput>> {
    T createNode(T newNode, Node<?> parent, NodeTreeIf.SetNode nodeSet, RepositoryAuth auth) throws SystemGlobalException;

    void deleteNode(UUID nodeId, RepositoryAuth auth) throws SystemGlobalException;
}
