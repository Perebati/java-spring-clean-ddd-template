package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.OperationActionSchema;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.mapper.OperationActionMapper;
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
        extends GenericRepositoryImpl<ActionOperationEntity, OperationActionSchema>
        implements ActionOperationRepository {

    ActionOperationRepositoryImpl(
            OperationActionMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(OperationActionSchema.class, entityManager));
    }
}
