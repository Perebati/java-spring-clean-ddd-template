package org.gfinnovation.dealsafe.modules.tree.domain.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeStaticRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Main repository interface of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRepository
 * @since 30/10/2024
 */

public interface TreeRepository {

    NodeTreeRepository getNodeTreeRepository();

    RootTreeDynamicRepository getRootTreeDynamicRepository();

    RootTreeRepository getRootTreeRepository();

    RootTreeStaticRepository getRootTreeStaticRepository();

    NodeTree createNode(@NotNull NodeTree newNode, @NotNull Object parent, @NotNull UUID parent_id, @NotNull RepositoryAuth auth) throws RepositoryException;

    Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException;

    RootTree updateGenericRootSync(@NotNull RootTree entity, @NotNull RepositoryAuth auth) throws RepositoryException, IllegalArgumentException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}