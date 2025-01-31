package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeIfDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NodeTreeIfDomainServiceImpl
        extends GenericDomainServiceImpl<NodeTreeIf, NodeTreeIfRepository>
        implements NodeTreeIfDomainService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;

    @Autowired
    NodeTreeIfDomainServiceImpl(
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
