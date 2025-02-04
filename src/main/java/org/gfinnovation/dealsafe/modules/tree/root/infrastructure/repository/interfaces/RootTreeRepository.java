package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeRepository
 * @since 30/10/2024
 */

public interface RootTreeRepository {
    Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}
