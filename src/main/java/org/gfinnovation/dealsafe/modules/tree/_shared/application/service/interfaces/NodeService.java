package org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeService
 * @since v1.0 (13/02/2025)
 */

public interface NodeService extends GenericService<Node<NodeInput>> {
    void deleteNode(UUID id) throws SystemGlobalException;
}
