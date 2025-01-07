package org.gfinnovation.dealsafe.modules.operation.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.operation.domain.ActionOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.domain.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.modules.operation.infrastructure.ActionOperationSchema;
import org.gfinnovation.dealsafe.modules.operation.infrastructure.mapper.ActionOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ActionOperationRepositoryImpl(
            ActionOperationMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(ActionOperationSchema.class, entityManager), ActionOperationSchema.class);
    }
}
