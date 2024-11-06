package org.gfinnovation.dealsafe.domains.input.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.entity.repository.InputRepository;
import org.gfinnovation.dealsafe.domains.input.infrastructure.persistence.InputSchema;
import org.gfinnovation.dealsafe.domains.input.infrastructure.persistence.mapper.InputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputRepositoryImpl
 * @since 30/10/2024
 */


@Repository
class InputRepositoryImpl
        extends GenericBusinessRepositoryImpl<InputEntity, InputSchema>
        implements InputRepository {

    @Autowired
    InputRepositoryImpl(
            InputMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(InputSchema.class, entityManager), InputSchema.class);
    }
}