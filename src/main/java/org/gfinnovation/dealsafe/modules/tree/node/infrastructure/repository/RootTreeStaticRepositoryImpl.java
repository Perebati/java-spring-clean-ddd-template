package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.node.domain.repository.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.aggregates.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.RootTreeStaticMapper;
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
