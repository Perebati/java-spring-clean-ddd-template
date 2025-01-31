package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper.RootTreeDynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeDynamicRepositoryImpl
 * @since 30/10/2024
 */

@Repository
class RootTreeDynamicRepositoryImpl
        extends GenericBusinessRepositoryImpl<RootTreeDynamic, RootTreeDynamicEntity>
        implements RootTreeDynamicRepository {
    @Autowired
    RootTreeDynamicRepositoryImpl(
            RootTreeDynamicMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeDynamicEntity.class, entityManager), RootTreeDynamicEntity.class);
    }
}