package org.gfinnovation.dealsafe._shared.modules.application.interfaces;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Every single Service class in this system should extend from this.
 * It sets a pattern for the whole system, witch is easy to follow.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class GenericService
 * @since v1.0 (30/11/2024)
 */
public interface GenericService<E extends GenericClass> {
    Optional<E> read(UUID id) throws SystemGlobalException;

    Optional<List<E>> readAll() throws SystemGlobalException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws SystemGlobalException;

    void check(UUID id) throws SystemGlobalException;

    void checkAll(Set<UUID> ids) throws SystemGlobalException;
}
