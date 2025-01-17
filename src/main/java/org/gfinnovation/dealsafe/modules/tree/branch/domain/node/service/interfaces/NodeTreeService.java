package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.branch.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeService extends GenericService<NodeTree> {
    NodeTree create(NodeCreationDTO nodeCreationDTO) throws ServiceException;
}
