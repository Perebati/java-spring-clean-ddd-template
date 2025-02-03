package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationIfDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

public interface NodeTreeIfService extends GenericService<NodeTreeIf> {
    NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws ServiceException;
    NodeTreeIf create(NodeCreationIfDTO nodeCreationDTO) throws ServiceException;
}
