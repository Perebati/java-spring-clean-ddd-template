package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationIfDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.repository.interfaces.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeBlockDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles business operations of Nodes in the validation tree.
 * Every node has a parent, that being either a Root or another Node.
 * Each node can have linked operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusinessImpl
 * @since 30/10/2024
 */

@Service
class NodeTreeBlockDomainServiceImpl
        extends GenericDomainServiceImpl<NodeTreeBlock, NodeTreeBlockRepository>
        implements NodeTreeBlockDomainService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;
    private final NodeTreeIfRepository nodeTreeIfRepository;

    @Autowired
    public NodeTreeBlockDomainServiceImpl(
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeRepository nodeRepository,
            NodeTreeIfRepository nodeTreeIfRepository
    ) {
        super(nodeTreeBlockRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeRepository = nodeRepository;
        this.nodeTreeIfRepository = nodeTreeIfRepository;
    }

    /**
     * Handles the creation of a Node.
     * Every Node needs a parent, in this case the parent can be either an RootNode or a common Node.
     * ParentId can either belong to a Root or a Node, this method supports both.
     *
     * @throws ServiceException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public NodeTreeBlock create(NodeCreationDTO nodeCreationDTO) throws ServiceException {
        try {
            Node<?> parent = this.nodeRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTreeBlock newNode = this.nodeTreeFactory.produceBlock(nodeCreationDTO.name(), parent);
            return this.read(this.repository.createNode(newNode, parent, getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    public NodeTreeBlock create(NodeCreationIfDTO nodeCreationDTO) throws ServiceException {
        try {
            NodeTreeIf parent = this.nodeTreeIfRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTreeBlock newNode = this.nodeTreeFactory.produceBlock(nodeCreationDTO.name(), parent);
            return this.read(
                    this.repository.createIfNode(
                            newNode,
                            parent,
                            nodeCreationDTO.position(),
                            getRepositoryAuth()).getId()
            );
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }
}
