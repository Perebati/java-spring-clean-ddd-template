package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeRepository
 * @since v1.0 (06/02/2025)
 */
public interface NodeRepository extends GenericBusinessRepository<Node<NodeInput>> {
    void deleteNode(UUID id, RepositoryAuth auth) throws SystemGlobalException;
}
