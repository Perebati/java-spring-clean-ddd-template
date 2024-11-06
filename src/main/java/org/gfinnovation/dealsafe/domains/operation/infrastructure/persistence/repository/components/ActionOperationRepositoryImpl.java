package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.ActionOperationSchema;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.mapper.ActionOperationMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationRepositoryImpl
 * @since 30/10/2024
 */
@Component
class ActionOperationRepositoryImpl
        extends GenericBusinessRepositoryImpl<ActionOperationEntity, ActionOperationSchema>
        implements ActionOperationRepository {

    ActionOperationRepositoryImpl(
            ActionOperationMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(ActionOperationSchema.class, entityManager), ActionOperationSchema.class);
    }
}
