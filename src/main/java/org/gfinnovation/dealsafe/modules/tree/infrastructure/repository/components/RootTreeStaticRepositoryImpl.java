package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper.RootTreeStaticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticRepositoryImpl
 * @since 30/10/2024
 */

@Component
class RootTreeStaticRepositoryImpl
        extends GenericBusinessRepositoryImpl<RootTreeStatic, RootTreeStaticEntity>
        implements RootTreeStaticRepository {

    @Autowired
    RootTreeStaticRepositoryImpl(
            RootTreeStaticMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeStaticEntity.class, entityManager), RootTreeStaticEntity.class);
    }
}
