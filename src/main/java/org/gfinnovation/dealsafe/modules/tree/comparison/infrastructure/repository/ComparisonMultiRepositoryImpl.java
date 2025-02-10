package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonMultiMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMultiRepositoryImpl
 * @since v1.0 (06/02/2025)
 */
@Repository
public class ComparisonMultiRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonMulti, ComparisonMultiEntity>
        implements ComparisonMultiRepository {

    @Autowired
    ComparisonMultiRepositoryImpl(
            ComparisonMultiMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonMultiEntity.class, entityManager), ComparisonMultiEntity.class);
    }
}