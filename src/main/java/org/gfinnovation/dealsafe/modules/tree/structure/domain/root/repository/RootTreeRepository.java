package org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTree;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeRepository
 * @since 30/10/2024
 */

public interface RootTreeRepository extends GenericBusinessRepository<RootTree<?>> {
    Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException;

    RootTree<?> updateGenericRootSync(@NotNull RootTree<?> entity, @NotNull RepositoryAuth auth) throws RepositoryException, IllegalArgumentException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}
