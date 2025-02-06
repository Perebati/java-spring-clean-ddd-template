package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper.RootTreeStaticMapper;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeStaticRepositoryImpl
 * @since v1.0 (30/11/2024)
 */
@Repository
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
