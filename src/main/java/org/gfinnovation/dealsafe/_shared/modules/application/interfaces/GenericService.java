package org.gfinnovation.dealsafe._shared.modules.application.interfaces;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe.modules.exception.models.layered.ServiceException;

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

public interface GenericService<E extends GenericClass> {
    E read(UUID id) throws ServiceException;

    CompletableFuture<E> updateAsync(E entity) throws ServiceException;

    E updateSync(E entity) throws ServiceException;

    void deleteSync(UUID id) throws ServiceException;

    void deleteAsync(UUID id) throws ServiceException;

    Optional<List<E>> readAll() throws ServiceException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws ServiceException;

    void check(UUID id) throws ServiceException;

    void checkAll(Set<UUID> ids) throws ServiceException;
}
