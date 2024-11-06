package org.gfinnovation.dealsafe._shared.application;

import org.gfinnovation.dealsafe._shared.entity.GenericEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface GenericBusinessOld
 * @since 06/11/2024
 */

public interface GenericBusinessOld<E extends GenericEntity> {
    Optional<E> read(UUID id) throws RuntimeException;

    E update(E entity) throws RuntimeException;

    void delete(UUID id) throws RuntimeException;

    Optional<List<E>> readAll() throws RuntimeException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws RuntimeException;

    void check(UUID id) throws RuntimeException;

    void checkAll(Set<UUID> ids) throws RuntimeException;
}