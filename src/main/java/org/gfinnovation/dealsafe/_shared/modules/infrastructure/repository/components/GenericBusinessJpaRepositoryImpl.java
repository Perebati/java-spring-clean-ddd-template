package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * Some repository implementations are manually created in this system, thus giving the developer
 * more control over the queries and the way the data is handled. This class is
 * a generic implementation of a repository that handles business entities.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericJpaRepository
 * @since 06/11/2024
 */

public class GenericBusinessJpaRepositoryImpl<S extends GenericBusinessSchema> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds an entity by its id, company id and user id.
     *
     * @param id          The id of the entity.
     * @param companyId   The id of the company.
     * @param entityClass The class of the entity.
     * @return An optional of the entity.
     * @author Lucas Batista Pereira
     * @since 07/01/2025
     */
    public Optional<S> findById(
            UUID id,
            UUID companyId,
            Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate idPredicate = cb.equal(root.get("id"), id);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(idPredicate, companyIdPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultStream().findFirst();
    }

    /**
     * Finds all entities by user id, company id and entity class.
     *
     * @param userId      The id of the user.
     * @param companyId   The id of the company.
     * @param entityClass The class of the entity.
     * @return A list of entities.
     * @author Lucas Batista Pereira
     * @since 07/01/2025
     */
    public List<S> findAll(
            UUID userId,
            UUID companyId,
            Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(userIdPredicate, companyIdPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Finds all entities by a list of ids. Also including user id, company id and entity class.
     *
     * @param userId      The id of the user.
     * @param companyId   The id of the company.
     * @param ids         The list of ids.
     * @param entityClass The class of the entity.
     * @return A list of entities.
     * @author Lucas Batista Pereira
     * @since 07/01/2025
     */
    public List<S> findAllByIds(
            UUID userId,
            UUID companyId,
            Set<UUID> ids,
            Class<S> entityClass
    ) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate idsPredicate = root.get("id").in(ids);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(userIdPredicate, companyIdPredicate, idsPredicate, notDeletedPredicate));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Finds all entities by user id, company id and entity class. Paginated.
     *
     * @param userId      The id of the user.
     * @param companyId   The id of the company.
     * @param pageable    The page request.
     * @param entityClass The class of the entity.
     * @return A page of entities.
     * @author Lucas Batista Pereira
     * @since 07/01/2025
     */
    public Page<S> findAllPaginated(
            UUID userId,
            UUID companyId,
            PageRequest pageable,
            Class<S> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = cb.createQuery(entityClass);
        Root<S> root = query.from(entityClass);

        Predicate userIdPredicate = cb.equal(root.get("user_id"), userId);
        Predicate companyIdPredicate = cb.equal(root.get("company_id"), companyId);
        Predicate notDeletedPredicate = cb.isFalse(root.get("deleted"));

        query.select(root).where(cb.and(userIdPredicate, companyIdPredicate, notDeletedPredicate));

        pageable.getSort();
        List<Order> orders = new ArrayList<>();
        for (Sort.Order sortOrder : pageable.getSort()) {
            Order order = sortOrder.isAscending()
                    ? cb.asc(root.get(sortOrder.getProperty()))
                    : cb.desc(root.get(sortOrder.getProperty()));
            orders.add(order);
        }
        query.orderBy(orders);

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<S> countRoot = countQuery.from(entityClass);
        countQuery.select(cb.count(countRoot));
        countQuery.where(cb.and(
                cb.equal(countRoot.get("user_id"), userId),
                cb.equal(countRoot.get("company_id"), companyId),
                cb.isFalse(countRoot.get("deleted"))
        ));

        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        List<S> content = entityManager.createQuery(query)
                .setFirstResult((int) (pageable.getOffset()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(content, pageable, totalCount);
    }
}