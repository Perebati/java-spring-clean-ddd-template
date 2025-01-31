package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionOperation;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationRepositoryImpl
 * @since 30/10/2024
 */
@Repository
class ActionOperationRepositoryImpl
        extends GenericBusinessRepositoryImpl<ActionOperation, ActionOperationEntity>
        implements ActionOperationRepository {
    @Autowired
    ActionOperationRepositoryImpl(
            ActionOperationMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(ActionOperationEntity.class, entityManager), ActionOperationEntity.class);
    }
}
