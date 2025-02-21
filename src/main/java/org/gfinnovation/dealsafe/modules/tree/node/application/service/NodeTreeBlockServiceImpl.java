package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
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
 * Every writing operation for NodeBlock should be defined here.
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
    private final NodeService nodeService;
    private final RootTreeService rootTreeService;
    private final NodeTreeService<NodeTreeBlock> nodeTreeService;

    private final NodeTreeFactory nodeTreeFactory;

    @Autowired
    public NodeTreeBlockServiceImpl(
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeService nodeService,
            NodeTreeService<NodeTreeBlock> nodeTreeService,
            RootTreeService rootTreeService
    ) {
        super(nodeTreeBlockRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeService = nodeService;
        this.nodeTreeService = nodeTreeService;
        this.rootTreeService = rootTreeService;
    }

    /**
     * Handles creation operation of a Node of type BLOCK.
     * It checks if parent node exists by reading it. Then creates a new node based on input.
     * Lastly, it keeps track of tree structure via Tree History.
     *
     * @param nodeCreationData Customized Input data for NodeBlock creation.
     * @return NodeTreeBlock
     * @throws SystemGlobalException DealSafe standard error.
     */
    @Override
    @Transactional
    public NodeTreeBlock createBlock(NodeCreationData nodeCreationData, Boolean keepHistory) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(nodeCreationData.parent_id());
            NodeTreeBlock newNode = this.nodeTreeFactory.produceBlock(nodeCreationData.name(), parent);
            newNode = this.nodeTreeService.createNode(newNode, parent, nodeCreationData.position());
            if(keepHistory) this.rootTreeService.keepHistory(newNode.getId());
            return newNode;
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a node block.", e);
        }
    }

    /**
     * Handles updates in NodeTreeBlock via customized input, it also keeps track
     * of tree history.
     *
     * @param id NodeBlock id.
     * @param nodeTreeBlockData Customized input for update operation.
     * @return NodeTreeBlock
     * @throws SystemGlobalException DealSafe standard error.
     */
    @Override
    @Transactional
    public NodeTreeBlock updateBlock(UUID id, NodeTreeBlockData nodeTreeBlockData, Boolean keepHistory) throws SystemGlobalException {
        try {
            NodeTreeBlock updatedNode = this.update(id, nodeTreeBlockData);
            if(keepHistory) this.rootTreeService.keepHistory(id);
            return updatedNode;
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong updating a node block.", e);
        }
    }
}