package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeRepository
 * @since v1.0 (06/02/2025)
 */
public interface NodeRepository extends GenericBusinessRepository<Node<NodeInput>> {
}
