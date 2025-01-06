package org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.domains.infrastructure.persistence.repository.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.company.entity.repository.CompanyRepository;
import org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.CompanySchema;
import org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.mapper.CompanyMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyRepositoryImpl
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@Repository
class CompanyRepositoryImpl
        extends GenericRepositoryImpl<CompanyEntity, CompanySchema>
        implements CompanyRepository {

    CompanyRepositoryImpl(
            CompanyMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(CompanySchema.class, entityManager));
    }
}