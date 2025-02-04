package org.gfinnovation.dealsafe.modules.tree.node.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
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
class NodeTreeBlockServiceImpl
        extends GenericServiceImpl<NodeTreeBlock, NodeTreeBlockRepository>
        implements NodeTreeBlockService {
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;
    private final NodeTreeRepository<NodeTreeBlock> nodeTreeRepository;

    @Autowired
    public NodeTreeBlockServiceImpl(
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeRepository nodeRepository,
            NodeTreeRepository<NodeTreeBlock> nodeTreeRepository
    ) {
        super(nodeTreeBlockRepository);
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeRepository = nodeRepository;
        this.nodeTreeRepository = nodeTreeRepository;
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
            return this.read(this.nodeTreeRepository.createNode(newNode, parent, nodeCreationDTO.position(), getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }
}
