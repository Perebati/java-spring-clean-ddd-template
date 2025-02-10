package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper.RootTreeDynamicMapper;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeDynamicRepositoryImpl
 * @since v1.0 (30/11/2024)
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