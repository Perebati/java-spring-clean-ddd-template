package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeDynamicSchema;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper.RootTreeDynamicMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryImpl
 * @since 30/10/2024
 */

@Component
class RootTreeDynamicRepositoryImpl
        extends GenericBusinessRepositoryImpl<RootTreeDynamicEntity, RootTreeDynamicSchema>
        implements RootTreeDynamicRepository {

    RootTreeDynamicRepositoryImpl(
            RootTreeDynamicMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeDynamicSchema.class, entityManager), RootTreeDynamicSchema.class);
    }
}