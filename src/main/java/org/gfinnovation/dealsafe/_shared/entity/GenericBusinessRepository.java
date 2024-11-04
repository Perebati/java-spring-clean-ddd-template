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
public interface GenericBusinessRepository<E extends GenericEntity> {

    E create(UUID user_id, UUID company_id, E entity) throws RuntimeException;

    Optional<E> read(UUID user_id, UUID company_id, UUID id) throws RuntimeException;

    E update(UUID user_id, UUID company_id, E entity) throws RuntimeException;

    void delete(UUID user_id, UUID company_id, UUID id) throws RuntimeException;

    Optional<List<E>> findAll(UUID user_id, UUID company_id) throws RuntimeException;

    Optional<List<E>> findAllByIds(UUID user_id, UUID company_id, List<UUID> ids) throws RuntimeException;

    void check(UUID user_id, UUID company_id, UUID id) throws RuntimeException;

    void checkAll(UUID user_id, UUID company_id, Set<UUID> ids) throws RuntimeException;
}
