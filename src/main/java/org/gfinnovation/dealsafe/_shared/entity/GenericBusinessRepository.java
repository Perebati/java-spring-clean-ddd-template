package org.gfinnovation.dealsafe._shared.entity;

import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Every repository in this system should use this Generic interface
 * for handling data in the database. This one is used for entities that
 * gets saved on a relational database, that being Postgres.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericRepository
 * @since 30/10/2024
 */

public interface GenericBusinessRepository<E extends GenericEntity> {

    CompletableFuture<E> createAsync(UUID user_id, UUID company_id, E entity) throws RepositoryException;

    E createSync(UUID user_id, UUID company_id, E entity) throws RepositoryException;

    E read(UUID user_id, UUID company_id, UUID id) throws RepositoryException;

    CompletableFuture<E> updateAsync(UUID user_id, UUID company_id, E entity) throws RepositoryException;

    E updateSync(UUID user_id, UUID company_id, E entity) throws RepositoryException;

    void deleteAsync(UUID user_id, UUID company_id, UUID id) throws RepositoryException;

    void deleteSync(UUID user_id, UUID company_id, UUID id) throws RepositoryException;

    Optional<List<E>> findAll(UUID user_id, UUID company_id) throws RepositoryException;

    Optional<List<E>> findAllByIds(UUID user_id, UUID company_id, List<UUID> ids) throws RepositoryException;

    void check(UUID user_id, UUID company_id, UUID id) throws RepositoryException, EntityNotFoundException;

    void checkAll(UUID user_id, UUID company_id, Set<UUID> ids) throws RepositoryException, EntityNotFoundException;
}
