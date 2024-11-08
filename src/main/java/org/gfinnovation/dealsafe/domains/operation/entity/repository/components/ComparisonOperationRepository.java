package org.gfinnovation.dealsafe.domains.operation.entity.repository.components;

import org.gfinnovation.dealsafe._shared.entity.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.authentication.RepositoryAuth;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonOperationRepository extends GenericBusinessRepository<ComparisonOperationEntity> {
    ComparisonOperationEntity createComparison(ComparisonOperationEntity newOperation, NodeTreeEntity parent, RepositoryAuth auth) throws RepositoryException;
}
