package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe.exception.models.InfrastructureException;

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

public interface GenericRepository<E extends GenericClass> {

    E create(E entity) throws InfrastructureException;

    Optional<E> read(UUID id) throws InfrastructureException;

    E update(E entity) throws InfrastructureException;

    void delete(UUID id) throws InfrastructureException;

    Optional<List<E>> findAll() throws InfrastructureException;

    Optional<List<E>> findAllByIds(List<UUID> ids) throws InfrastructureException;

    void check(UUID id) throws InfrastructureException;

    void checkAll(Set<UUID> ids) throws InfrastructureException;
}
