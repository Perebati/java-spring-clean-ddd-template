package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

public interface NodeTreeIfService extends GenericService<NodeTreeIf> {
    NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws SystemGlobalException;
}
