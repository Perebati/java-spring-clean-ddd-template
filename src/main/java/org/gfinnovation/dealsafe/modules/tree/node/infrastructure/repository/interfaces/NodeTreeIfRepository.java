package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

public interface NodeTreeIfRepository extends GenericBusinessRepository<NodeTreeIf> {
    NodeTreeIf createNode(
            NodeTreeIf newNode,
            Node<?> parent,
            RepositoryAuth auth
    ) throws RepositoryException;
}