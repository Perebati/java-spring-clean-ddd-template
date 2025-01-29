package org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.repository.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.tree.condition.infrastructure.mapper.ComparisonOperationMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationRepositoryImpl
 * @since 30/10/2024
 */
@Component
class ComparisonSingularRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonSingular, ComparisonOperationEntity>
        implements ComparisonSingularRepository {

    private final NodeTreeBlockRepository nodeTreeBlockRepository;

    ComparisonSingularRepositoryImpl(
            ComparisonOperationMapper mapper,
            EntityManager entityManager,
            NodeTreeBlockRepository nodeTreeBlockRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonOperationEntity.class, entityManager), ComparisonOperationEntity.class);
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
    }

    @Transactional
    public ComparisonSingular createComparison(ComparisonSingular newOperation, NodeTree<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
//            LinkedList<Condition<?>> lista = new LinkedList<>();
//            lista.add(newOperation);
//            newOperation = this.createSync(newOperation, auth);
//            parent.addNode((new NodeTreeIf("nome", parent, lista)));
//            this.nodeTreeBlockRepository.updateSync(parent, auth);
//            return this.read(newOperation.getId(), auth);
            return null;
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong on a transaction to save a comparison operation.");
        }
    }
}

