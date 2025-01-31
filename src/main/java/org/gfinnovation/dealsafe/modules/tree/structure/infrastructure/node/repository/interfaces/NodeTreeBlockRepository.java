package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.repository.interfaces;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeBlockRepository
 * @since 30/10/2024
 */

public interface NodeTreeBlockRepository extends GenericBusinessRepository<NodeTreeBlock> {
    NodeTree<?> createNode(
            @NotNull NodeTreeBlock newNode,
            @NotNull Node<?> parent,
            @NotNull RepositoryAuth auth
    ) throws RepositoryException;

    NodeTreeBlock createIfNode(
            NodeTreeBlock newNode,
            NodeTreeIf parent,
            NodeTreeIf.SetNode position,
            RepositoryAuth auth
    ) throws RepositoryException;
}
