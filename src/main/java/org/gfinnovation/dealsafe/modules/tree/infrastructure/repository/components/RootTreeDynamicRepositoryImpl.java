package org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.RootTreeDynamicSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.mapper.RootTreeDynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RootTreeDynamicRepositoryImpl(
            RootTreeDynamicMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeDynamicSchema.class, entityManager), RootTreeDynamicSchema.class);
    }
}