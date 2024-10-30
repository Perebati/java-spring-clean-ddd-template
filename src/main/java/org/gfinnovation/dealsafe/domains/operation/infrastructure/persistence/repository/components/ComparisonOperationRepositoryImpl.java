package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ComparisonOperationRepository;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.OperationComparisonSchema;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.mapper.OperationComparisonMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationRepositoryImpl
 * @authorNote n/a
 * @since 30/10/2024
 */
@Component
class ComparisonOperationRepositoryImpl
        extends GenericRepositoryImpl<ComparisonOperationEntity, OperationComparisonSchema>
        implements ComparisonOperationRepository {

    ComparisonOperationRepositoryImpl(
            OperationComparisonMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(OperationComparisonSchema.class, entityManager));
    }
}

