package org.gfinnovation.dealsafe.domains.operation.entity.repository.components;

import org.gfinnovation.dealsafe._shared.entity.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonOperationRepository extends GenericBusinessRepository<ComparisonOperationEntity> {
    ComparisonOperationEntity createComparison(UUID user_id, UUID company_id, ComparisonOperationEntity newOperation, NodeTreeEntity parent) throws RepositoryException;
}
