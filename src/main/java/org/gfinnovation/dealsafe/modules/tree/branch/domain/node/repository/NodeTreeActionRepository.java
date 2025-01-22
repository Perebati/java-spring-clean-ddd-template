package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.repository;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTreeAction;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeActionRepository
 * @since 22/01/2025
 */

public interface NodeTreeActionRepository extends GenericBusinessRepository<NodeTreeAction> {
    NodeTreeAction createNode(NodeTreeAction newNode, Branch parent, RepositoryAuth auth) throws RepositoryException;
}
