package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BranchRepositoryImpl
 * @since 20/01/2025
 */

@Component
class BranchRepositoryImpl
        extends GenericBusinessRepositoryImpl<Branch, BranchEntity>
        implements BranchRepository {
    private final EntityManager entityManager;

    @Autowired
    BranchRepositoryImpl(
            BranchMapper mapper,
            EntityManager entityManager, EntityManager entityManager1) {
        super(mapper, new SimpleJpaRepository<>(BranchEntity.class, entityManager), BranchEntity.class);
        this.entityManager = entityManager1;
    }
    public String findTypeByBranchId(UUID branchId) {
        String sql = "SELECT tb.type FROM tree_branch tb WHERE tb.id = ?1";

        try {
            return (String) entityManager
                    .createNativeQuery(sql)
                    .setParameter(1, branchId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}