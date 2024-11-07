package org.gfinnovation.dealsafe._shared.application;

import org.gfinnovation.dealsafe._shared.entity.GenericEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Every single business class in this system should extend from this.
 * It sets a pattern for the whole system, witch is easy to follow.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericBusinessInterface
 * @since 30/10/2024
 */

public interface GenericBusiness<E extends GenericEntity> {
    E read(UUID id) throws RuntimeException;

    CompletableFuture<E> updateAsync(E entity) throws RuntimeException;

    E updateSync(E entity) throws RuntimeException;

    void deleteSync(UUID id) throws RuntimeException;

    void deleteAsync(UUID id) throws RuntimeException;

    Optional<List<E>> readAll() throws RuntimeException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws RuntimeException;

    void check(UUID id) throws RuntimeException;

    void checkAll(Set<UUID> ids) throws RuntimeException;
}
