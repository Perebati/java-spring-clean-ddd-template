package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    private final NodeRepository nodeRepository;

    @Autowired
    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeTreeService<NodeTreeIf> nodeTreeService,
            NodeRepository nodeRepository
    ) {
        super(nodeTreeIfRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeTreeService = nodeTreeService;
        this.nodeRepository = nodeRepository;
    }

    @Override
    public NodeTreeIf createIf(NodeCreationData nodeCreationData) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeRepository.read(nodeCreationData.parent_id(), getRepositoryAuth());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            return this.read(this.nodeTreeService.createNode(newNode, parent, nodeCreationData.position(), getRepositoryAuth()).getId());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a if node.");
        }
    }

    @Override
    public void deleteIf(UUID id) throws SystemGlobalException {
        try {
            this.delete(id);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong deleting a if node.");
        }
    }
}