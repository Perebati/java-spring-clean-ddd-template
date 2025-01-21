package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.repository;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeRepository
 * @since 30/10/2024
 */

public interface NodeTreeRepository extends GenericBusinessRepository<NodeTree> {
    NodeTree createNode(@NotNull NodeTree newNode, @NotNull Branch parent, @NotNull RepositoryAuth auth) throws RepositoryException;
}
