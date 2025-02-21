package org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlockService
 * @since v1.0 (30/11/2024)
 */
public interface NodeTreeBlockService extends GenericService<NodeTreeBlock> {
    NodeTreeBlock createBlock(NodeCreationData nodeCreationData, Boolean keepHistory) throws SystemGlobalException;
    NodeTreeBlock updateBlock(UUID id, NodeTreeBlockData nodeTreeBlockData, Boolean keepHistory) throws SystemGlobalException;
}
