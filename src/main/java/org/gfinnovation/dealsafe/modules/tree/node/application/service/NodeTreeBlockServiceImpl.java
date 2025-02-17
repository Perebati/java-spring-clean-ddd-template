package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Handles business operations of Nodes in the validation tree.
 * Every node has a parent, that being either a Root or another Node.
 * Each node can have linked operations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeServiceImpl
 * @since v1.0 (30/11/2024)
 */
@Service
class NodeTreeBlockServiceImpl
        extends GenericServiceImpl<NodeTreeBlock, NodeTreeBlockRepository>
        implements NodeTreeBlockService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeService nodeService;
    private final NodeTreeService<NodeTreeBlock> nodeTreeService;

    @Autowired
    public NodeTreeBlockServiceImpl(
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeService nodeService,
            NodeTreeService<NodeTreeBlock> nodeTreeService
    ) {
        super(nodeTreeBlockRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeService = nodeService;
        this.nodeTreeService = nodeTreeService;
    }

    /**
     * Handles the creation of a Node.
     * Every Node needs a parent, in this case the parent can be either an RootNode or a common Node.
     * ParentId can either belong to a Root or a Node, this method supports both.
     *
     * @throws ApplicationException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */

    @Override
    public NodeTreeBlock createBlock(NodeCreationData nodeCreationData) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(nodeCreationData.parent_id());
            NodeTreeBlock newNode = this.nodeTreeFactory.produceBlock(nodeCreationData.name(), parent);
            return this.read(this.nodeTreeService.createNode(newNode, parent, nodeCreationData.position()).getId());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a node block.", e);
        }
    }

    @Override
    public NodeTreeBlock updateBlock(UUID id,
                                     NodeTreeBlockData nodeTreeBlockData) throws SystemGlobalException {
        try {
            return this.update(id, nodeTreeBlockData);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong updating a node block.", e);
        }
    }
}