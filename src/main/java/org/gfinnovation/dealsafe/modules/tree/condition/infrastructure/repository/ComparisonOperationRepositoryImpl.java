package org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTreeCondition;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.repository.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.Condition;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.Comparison;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.repository.ComparisonOperationRepository;
import org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.mapper.ComparisonOperationMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationRepositoryImpl
 * @since 30/10/2024
 */
@Component
class ComparisonOperationRepositoryImpl
        extends GenericBusinessRepositoryImpl<Comparison, ComparisonOperationEntity>
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
    public Comparison createComparison(Comparison newOperation, NodeTree parent, RepositoryAuth auth) throws RepositoryException {
        try {
            LinkedList<Condition<?>> lista = new LinkedList<>();
            lista.add(newOperation);
            newOperation = this.createSync(newOperation, auth);
            parent.addNode((new NodeTreeCondition("nome", parent, lista)));
            this.nodeTreeRepository.updateSync(parent, auth);
            return this.read(newOperation.getId(), auth);
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong on a transaction to save a comparison operation.");
        }
    }
}

