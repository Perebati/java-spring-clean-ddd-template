package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces;

import jakarta.annotation.Nonnull;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

public interface GenericBusinessRepository<E extends GenericClass> {

    CompletableFuture<E> createAsync(@Nonnull E entity, @Nonnull RepositoryAuth auth) throws RepositoryException;

    E createSync(@Nonnull E entity, @Nonnull RepositoryAuth auth) throws RepositoryException;

    E read(@Nonnull UUID id, @Nonnull RepositoryAuth auth) throws RepositoryException;

    CompletableFuture<E> updateAsync(@Nonnull E entity, @Nonnull RepositoryAuth auth) throws RepositoryException;

    E updateSync(@Nonnull E entity, @Nonnull RepositoryAuth auth) throws RepositoryException;

    void deleteAsync(@Nonnull UUID id, @Nonnull RepositoryAuth auth) throws RepositoryException;

    void deleteSync(@Nonnull UUID id, @Nonnull RepositoryAuth auth) throws RepositoryException;

    Optional<List<E>> findAll(@Nonnull RepositoryAuth auth) throws RepositoryException;

    Page<E> findAllPaginated(@Nonnull PageRequest pageRequest, @Nonnull RepositoryAuth auth) throws RepositoryException;

    Optional<List<E>> findAllByIds(@Nonnull List<UUID> ids, @Nonnull RepositoryAuth auth) throws RepositoryException;

    void check(@Nonnull UUID id, @Nonnull RepositoryAuth auth) throws RepositoryException;

    void checkAll(@Nonnull Set<UUID> ids, @Nonnull RepositoryAuth auth) throws RepositoryException;
}