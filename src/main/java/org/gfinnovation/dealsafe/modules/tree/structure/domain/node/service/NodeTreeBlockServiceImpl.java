package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

@Component
class NodeTreeBlockServiceImpl extends GenericServiceImpl implements NodeTreeBlockService {
    private final NodeTreeBlockRepository nodeTreeBlockRepository;
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;

    @Autowired
    public NodeTreeBlockServiceImpl(
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeRepository nodeRepository
    ) {
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeRepository = nodeRepository;
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
            return this.read(this.nodeTreeBlockRepository.createNode(newNode, parent, getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public NodeTreeBlock read(UUID id) throws ServiceException {
        try {
            return this.nodeTreeBlockRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a node.", e);
        }
    }

    @Override
    public CompletableFuture<NodeTreeBlock> updateAsync(NodeTreeBlock entity) throws ServiceException {
        try {
            return this.nodeTreeBlockRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a node.", e);
        }
    }

    @Override
    public NodeTreeBlock updateSync(NodeTreeBlock entity) throws ServiceException {
        try {
            return this.nodeTreeBlockRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a node.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.nodeTreeBlockRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a node.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.nodeTreeBlockRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a node.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeBlock>> readAll() throws ServiceException {
        try {
            return this.nodeTreeBlockRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeBlock>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.nodeTreeBlockRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.nodeTreeBlockRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.nodeTreeBlockRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all nodes by ids.", e);
        }
    }
}
