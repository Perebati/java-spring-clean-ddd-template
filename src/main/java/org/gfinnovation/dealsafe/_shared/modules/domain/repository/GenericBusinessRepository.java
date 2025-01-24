package org.gfinnovation.dealsafe._shared.modules.domain.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
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

    CompletableFuture<E> createAsync(E entity, RepositoryAuth auth) throws RepositoryException;

    E createSync(E entity, RepositoryAuth auth) throws RepositoryException;

    E read(UUID id, RepositoryAuth auth) throws RepositoryException;

    CompletableFuture<E> updateAsync(E entity, RepositoryAuth auth) throws RepositoryException;

    E updateSync(E entity, RepositoryAuth auth) throws RepositoryException;

    void deleteAsync(UUID id, RepositoryAuth auth) throws RepositoryException;

    void deleteSync(UUID id, RepositoryAuth auth) throws RepositoryException;

    Optional<List<E>> findAll(RepositoryAuth auth) throws RepositoryException;

    Page<E> findAllPaginated(PageRequest pageRequest, @NotNull RepositoryAuth auth) throws RepositoryException;

    Optional<List<E>> findAllByIds(List<UUID> ids, RepositoryAuth auth) throws RepositoryException;

    void check(UUID id, RepositoryAuth auth) throws RepositoryException;

    void checkAll(Set<UUID> ids, RepositoryAuth auth) throws RepositoryException;
}
