package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.CompanyListEntity;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.mapper.CompanyListMapper;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.repository.interfaces.CompanyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
class CompanyListRepositoryImpl
        extends GenericBusinessRepositoryImpl<CompanyList, CompanyListEntity>
        implements CompanyListRepository {

    @Autowired
    CompanyListRepositoryImpl(
            CompanyListMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(CompanyListEntity.class, entityManager), CompanyListEntity.class);
    }
}
