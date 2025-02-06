package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlockService
 * @since v1.0 (30/11/2024)
 */
public interface NodeTreeBlockService extends GenericService<NodeTreeBlock> {
    NodeTreeBlock create(NodeCreationDTO nodeCreationDTO) throws SystemGlobalException;
}
