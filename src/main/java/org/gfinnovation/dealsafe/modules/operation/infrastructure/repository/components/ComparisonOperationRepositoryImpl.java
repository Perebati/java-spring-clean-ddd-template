package org.gfinnovation.dealsafe.modules.operation.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.operation.domain.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.domain.repository.components.ComparisonOperationRepository;
import org.gfinnovation.dealsafe.modules.operation.infrastructure.ComparisonOperationSchema;
import org.gfinnovation.dealsafe.modules.operation.infrastructure.mapper.ComparisonOperationMapper;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
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
        extends GenericBusinessRepositoryImpl<ComparisonOperationEntity, ComparisonOperationSchema>
        implements ComparisonOperationRepository {

    private final TreeRepository treeRepository;

    ComparisonOperationRepositoryImpl(
            ComparisonOperationMapper mapper,
            EntityManager entityManager,
            TreeRepository treeRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonOperationSchema.class, entityManager), ComparisonOperationSchema.class);
        this.treeRepository = treeRepository;
    }

    @Transactional
    public ComparisonOperationEntity createComparison(ComparisonOperationEntity newOperation, NodeTreeEntity parent, RepositoryAuth auth) throws RepositoryException {
        try {
            newOperation = this.createSync(newOperation, auth);
            this.treeRepository.getNodeTreeRepository().updateSync(parent.addOperation(newOperation), auth);
            return this.read(newOperation.getId(), auth);
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong on a transaction to save a comparison operation.");
        }
    }
}

