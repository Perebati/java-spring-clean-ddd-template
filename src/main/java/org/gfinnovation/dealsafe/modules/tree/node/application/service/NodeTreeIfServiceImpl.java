package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
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
    private final NodeTreeRepository<NodeTreeIf> nodeTreeRepository;
    private final NodeRepository nodeRepository;

    @Autowired
    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeTreeRepository<NodeTreeIf> nodeTreeRepository,
            NodeRepository nodeRepository
    ) {
        super(nodeTreeIfRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeTreeRepository = nodeTreeRepository;
        this.nodeRepository = nodeRepository;
    }

    public NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            return this.read(this.nodeTreeRepository.createNode(newNode, parent, nodeCreationDTO.position(), getRepositoryAuth()).getId());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking a node.");
        }
    }
}