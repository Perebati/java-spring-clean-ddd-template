package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeSchema;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper.RootTreeMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeRepositoryImpl
 * @authorNote n/a
 * @since 30/10/2024
 */
@Component
class RootTreeRepositoryImpl
        extends GenericRepositoryImpl<RootTreeEntity, RootTreeSchema>
        implements RootTreeRepository {

    RootTreeRepositoryImpl(
            RootTreeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeSchema.class, entityManager));
    }
}