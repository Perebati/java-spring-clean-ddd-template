package org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.node.domain.repository.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.repository.ComparisonOperationRepository;
import org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.mapper.ComparisonOperationMapper;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationRepositoryImpl
 * @since 30/10/2024
 */
@Component
class ComparisonOperationRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonOperation, ComparisonOperationEntity>
        implements ComparisonOperationRepository {

    private final NodeTreeRepository nodeTreeRepository;

    ComparisonOperationRepositoryImpl(
            ComparisonOperationMapper mapper,
            EntityManager entityManager,
            NodeTreeRepository nodeTreeRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonOperationEntity.class, entityManager), ComparisonOperationEntity.class);
        this.nodeTreeRepository = nodeTreeRepository;
    }

    @Transactional
    public ComparisonOperation createComparison(ComparisonOperation newOperation, NodeTree parent, RepositoryAuth auth) throws RepositoryException {
        try {
            newOperation = this.createSync(newOperation, auth);
            this.nodeTreeRepository.updateSync(parent.addOperation(newOperation), auth);
            return this.read(newOperation.getId(), auth);
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong on a transaction to save a comparison operation.");
        }
    }
}

