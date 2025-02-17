package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeTreeIfServiceImpl
 * @since v1.0 (06/02/2025)
 */
@Service
class NodeTreeIfServiceImpl
        extends GenericServiceImpl<NodeTreeIf, NodeTreeIfRepository>
        implements NodeTreeIfService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeTreeService<NodeTreeIf> nodeTreeService;
    private final NodeService nodeService;

    @Autowired
    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeTreeService<NodeTreeIf> nodeTreeService,
            NodeService nodeService
    ) {
        super(nodeTreeIfRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeTreeService = nodeTreeService;
        this.nodeService = nodeService;
    }

    @Override
    public NodeTreeIf createIf(NodeIfCreationData nodeCreationData) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(nodeCreationData.parent_id());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            return this.read(this.nodeTreeService.createNode(newNode, parent, nodeCreationData.position()).getId());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a if node.");
        }
    }
}