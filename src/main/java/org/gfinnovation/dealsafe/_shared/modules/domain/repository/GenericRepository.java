package org.gfinnovation.dealsafe._shared.modules.domain.repository;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericEntity;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

public interface GenericRepository<E extends GenericEntity> {

    E create(E entity) throws RepositoryException;

    Optional<E> read(UUID id) throws RepositoryException;

    E update(E entity) throws RepositoryException;

    void delete(UUID id) throws RepositoryException;

    Optional<List<E>> findAll() throws RepositoryException;

    Optional<List<E>> findAllByIds(List<UUID> ids) throws RepositoryException;

    void check(UUID id) throws RepositoryException;

    void checkAll(Set<UUID> ids) throws RepositoryException;
}
