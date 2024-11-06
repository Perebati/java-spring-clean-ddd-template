package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericJpaRepository
 * @since 06/11/2024
 */

public class GenericBusinessJpaRepository<S extends GenericBusinessSchema> {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<S> findByIdCompanyIdUserIdAndNotDeleted(UUID id, UUID companyId, UUID userId, Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate idPredicate = cb.equal(root.get("id"), id);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(idPredicate, companyIdPredicate, userIdPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultStream().findFirst();
    }

    public List<S> findAllByUserIdAndCompanyIdAndDeletedFalse(UUID userId, UUID companyId, Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(userIdPredicate, companyIdPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultList();
    }

    public List<S> findAllByUserIdAndCompanyIdAndIdInAndDeletedFalse(UUID userId, UUID companyId, Set<UUID> ids, Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate idsPredicate = root.get("id").in(ids);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(userIdPredicate, companyIdPredicate, idsPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultList();
    }
}