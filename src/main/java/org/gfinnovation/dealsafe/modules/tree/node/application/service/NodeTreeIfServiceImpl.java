package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * NodeIfs are a special type of Node that can fork the way of how the validation tree
 * is traversed. This class handles Writing and Reading of operations of said class.
 *
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeTreeIfServiceImpl
 * @since v1.0 (06/02/2025)
 */
@Service
class NodeTreeIfServiceImpl
        extends GenericServiceImpl<NodeTreeIf, NodeTreeIfRepository>
        implements NodeTreeIfService {
    private final NodeService nodeService;
    private final RootTreeService rootTreeService;
    private final NodeTreeService<NodeTreeIf> nodeTreeService;

    private final NodeTreeFactory nodeTreeFactory;

    @Autowired
    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            RootTreeService rootTreeService,
            NodeTreeFactory nodeTreeFactory,
            NodeTreeService<NodeTreeIf> nodeTreeService,
            NodeService nodeService
    ) {
        super(nodeTreeIfRepository);
        this.rootTreeService = rootTreeService;
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeTreeService = nodeTreeService;
        this.nodeService = nodeService;
    }

    /**
     * Handles creation operation of a Node of type IF.
     * It checks if parent node exists by reading it. Then creates a new node based on input.
     * Lastly, it keeps track of tree structure via Tree History.
     *
     * @param nodeCreationData Customized Input data for NodeBlock creation.
     * @return NodeTreeIf
     * @throws SystemGlobalException DealSafe standard error.
     */
    @Override
    public NodeTreeIf createIf(NodeIfCreationData nodeCreationData, Boolean keepHistory) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(nodeCreationData.parent_id());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            newNode = this.nodeTreeService.createNode(newNode, parent, nodeCreationData.position());
            if(keepHistory) this.rootTreeService.keepHistory(newNode.getId());
            return newNode;
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a if node.", e);
        }
    }
}