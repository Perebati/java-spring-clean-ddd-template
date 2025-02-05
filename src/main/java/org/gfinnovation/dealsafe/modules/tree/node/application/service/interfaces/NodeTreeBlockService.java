package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeBlockService extends GenericService<NodeTreeBlock> {
    NodeTreeBlock create(NodeCreationDTO nodeCreationDTO) throws SystemGlobalException;
}
