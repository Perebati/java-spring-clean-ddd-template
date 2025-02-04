package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonMultiMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

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