package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonCustomListEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper.ComparisonCustomListMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonCustomListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ComparisonCustomListRepositoryImpl
        extends GenericBusinessRepositoryImpl<ComparisonCustomList, ComparisonCustomListEntity>
        implements ComparisonCustomListRepository {

    @Autowired
    ComparisonCustomListRepositoryImpl(
            ComparisonCustomListMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(ComparisonCustomListEntity.class, entityManager), ComparisonCustomListEntity.class);
    }
}
