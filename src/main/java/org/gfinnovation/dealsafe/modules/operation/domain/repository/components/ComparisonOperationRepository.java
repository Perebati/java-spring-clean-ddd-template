package org.gfinnovation.dealsafe.modules.operation.domain.repository.components;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.operation.domain.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonOperationRepository extends GenericBusinessRepository<ComparisonOperationEntity> {
    ComparisonOperationEntity createComparison(ComparisonOperationEntity newOperation, NodeTree parent, RepositoryAuth auth) throws RepositoryException;
}
