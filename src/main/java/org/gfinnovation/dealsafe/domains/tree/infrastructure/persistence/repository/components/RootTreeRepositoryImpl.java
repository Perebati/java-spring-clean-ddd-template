package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.persistence.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.RootTreeSchema;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper.RootTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeRepositoryImpl
 * @since 30/10/2024
 */

@Component
class RootTreeRepositoryImpl
        extends GenericBusinessRepositoryImpl<RootTreeEntity, RootTreeSchema>
        implements RootTreeRepository {

    @Autowired
    RootTreeRepositoryImpl(
            RootTreeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(RootTreeSchema.class, entityManager), RootTreeSchema.class);
    }
}