package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NodeTreeIfServiceImpl
        extends GenericDomainServiceImpl<NodeTreeIf, NodeTreeIfRepository>
        implements NodeTreeIfService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;

    @Autowired
    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeRepository nodeRepository
    ) {
        super(nodeTreeIfRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeRepository = nodeRepository;
    }

    public NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws ServiceException {
        try {
            Node<?> parent = this.nodeRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            return this.read(this.repository.createNode(newNode, parent, getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }
}
