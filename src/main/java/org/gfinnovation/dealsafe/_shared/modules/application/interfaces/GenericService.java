package org.gfinnovation.dealsafe._shared.modules.application.interfaces;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericEntity;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;

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

public interface GenericService<E extends GenericEntity> {
    E read(UUID id) throws BusinessException;

    CompletableFuture<E> updateAsync(E entity) throws BusinessException;

    E updateSync(E entity) throws BusinessException;

    void deleteSync(UUID id) throws BusinessException;

    void deleteAsync(UUID id) throws BusinessException;

    Optional<List<E>> readAll() throws BusinessException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws BusinessException;

    void check(UUID id) throws BusinessException;

    void checkAll(Set<UUID> ids) throws BusinessException;
}
