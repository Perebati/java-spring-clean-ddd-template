package org.gfinnovation.dealsafe.modules.tree.operation.domain.repository;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonOperationRepository extends GenericBusinessRepository<ComparisonOperation> {
    ComparisonOperation createComparison(ComparisonOperation newOperation, NodeTree parent, RepositoryAuth auth) throws RepositoryException;
}
