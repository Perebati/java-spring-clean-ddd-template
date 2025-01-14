package org.gfinnovation.dealsafe.modules.tree.domain.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeDynamicRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeNodeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeStaticRootRepository;

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

    TreeNodeRepository getTreeNodeRepository();

    TreeDynamicRootRepository getTreeDynamicRootRepository();

    TreeRootRepository getTreeRootRepository();

    TreeStaticRootRepository getTreeStaticRootRepository();

    TreeNode createNode(@NotNull TreeNode newNode, @NotNull Object parent, @NotNull UUID parent_id, @NotNull RepositoryAuth auth) throws RepositoryException;

    Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException;

    TreeRoot updateGenericRootSync(@NotNull TreeRoot entity, @NotNull RepositoryAuth auth) throws RepositoryException, IllegalArgumentException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}