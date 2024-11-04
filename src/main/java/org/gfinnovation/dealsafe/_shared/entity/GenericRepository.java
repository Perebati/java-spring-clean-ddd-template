package org.gfinnovation.dealsafe._shared.entity;

import org.springframework.stereotype.Repository;

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

@Repository
public interface GenericRepository<E extends GenericEntity> {

    E create(E entity) throws RuntimeException;

    Optional<E> read(UUID id) throws RuntimeException;

    E update(E entity) throws RuntimeException;

    void delete(UUID id) throws RuntimeException;

    Optional<List<E>> findAll() throws RuntimeException;

    Optional<List<E>> findAllByIds(List<UUID> ids) throws RuntimeException;

    void check(UUID id) throws RuntimeException;

    void checkAll(Set<UUID> ids) throws RuntimeException;
}
