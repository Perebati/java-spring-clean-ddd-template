package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeStaticSchema;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper.RootTreeStaticMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeStaticRepositoryImpl
 * @authorNote n/a
 * @since 30/10/2024
 */
@Component
class RootTreeStaticRepositoryImpl
        extends GenericRepositoryImpl<RootTreeStaticEntity, RootTreeStaticSchema>
        implements RootTreeStaticRepository {

    RootTreeStaticRepositoryImpl(
            RootTreeStaticMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeStaticSchema.class, entityManager));
    }
}
