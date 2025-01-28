package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.modules.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeService extends GenericService<NodeTreeBlock> {
    NodeTree<?> create(NodeCreationDTO nodeCreationDTO) throws ServiceException;

    NodeTreeAction createAction(NodeCreationDTO nodeCreationDTO);
}
