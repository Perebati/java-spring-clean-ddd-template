package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;

public interface NodeTreeIfService extends GenericService<NodeTreeIf> {
    NodeTreeIf createIf(NodeIfCreationData nodeCreationData, Boolean keepHistory) throws SystemGlobalException;
}
