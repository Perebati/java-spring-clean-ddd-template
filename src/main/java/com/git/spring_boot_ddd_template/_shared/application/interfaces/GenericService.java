package com.git.spring_boot_ddd_template._shared.application.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.git.spring_boot_ddd_template._shared.domain.GenericClass;
import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * Every single Service class in this system should extend from this.
 * It sets a pattern for the whole system, witch is easy to follow.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since v1.0 (30/11/2024)
 */
public interface GenericService<E extends GenericClass> {
    E read(UUID id) throws SystemGlobalException;

    Optional<E> findById(UUID id) throws SystemGlobalException;

    Optional<List<E>> readAll() throws SystemGlobalException;

    Optional<List<E>> readAllByIds(List<UUID> ids) throws SystemGlobalException;

    void check(UUID id) throws SystemGlobalException;

    void checkAll(Set<UUID> ids) throws SystemGlobalException;
}
