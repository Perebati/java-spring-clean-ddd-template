package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

public interface NodeTreeIfRepository extends GenericBusinessRepository<NodeTreeIf> {
    NodeTreeIf createNode(
            NodeTreeIf newNode,
            Node<?> parent,
            RepositoryAuth auth
    ) throws RepositoryException;

    NodeTreeIf createIfNode(
            NodeTreeIf newNode,
            NodeTreeIf parent,
            NodeTreeIf.SetNode position,
            RepositoryAuth auth
    ) throws RepositoryException;
}