package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.springframework.stereotype.Service;

public interface NodeTreeIfDomainService extends GenericService<NodeTreeIf> {
    NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws ServiceException;
}
