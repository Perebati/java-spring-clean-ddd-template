package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeActionRepository
 * @since 22/01/2025
 */

public interface NodeTreeActionRepository extends GenericBusinessRepository<NodeTreeAction> {
    NodeTreeAction createNode(NodeTreeAction newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException;
}
