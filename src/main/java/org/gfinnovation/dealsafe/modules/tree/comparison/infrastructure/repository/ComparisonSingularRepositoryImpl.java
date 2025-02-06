package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonSingularMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ComparisonOperationRepositoryImpl
 * @since v1.0 (30/11/2024)
 */
@Repository
class ComparisonSingularRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonSingular, ComparisonSingularEntity>
        implements ComparisonSingularRepository {

    @Autowired
    ComparisonSingularRepositoryImpl(
            ComparisonSingularMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonSingularEntity.class, entityManager), ComparisonSingularEntity.class);
    }
}