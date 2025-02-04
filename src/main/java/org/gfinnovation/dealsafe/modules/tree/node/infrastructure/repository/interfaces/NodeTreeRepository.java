package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeRepository
 * @since 24/01/2025
 */

public interface NodeTreeRepository<T extends NodeTree<?>> {
    T createNode(T newNode, Node<?> parent, NodeTreeIf.SetNode nodeSet, RepositoryAuth auth) throws RepositoryException;
}
