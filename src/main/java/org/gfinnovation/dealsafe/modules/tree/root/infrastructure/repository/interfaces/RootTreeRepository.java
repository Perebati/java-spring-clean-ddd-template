package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeRepository
 * @since v1.0 (30/11/2024)
 */
public interface RootTreeRepository {
    Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws SystemGlobalException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId) throws SystemGlobalException;
}
