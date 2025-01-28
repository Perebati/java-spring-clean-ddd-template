package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeBlockRepository
 * @since 30/10/2024
 */

public interface NodeTreeBlockRepository extends GenericBusinessRepository<NodeTreeBlock> {
    NodeTree<?> createNode(@NotNull NodeTreeBlock newNode, @NotNull Node<?> parent, @NotNull RepositoryAuth auth) throws RepositoryException;
}
