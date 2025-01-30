package org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeIfService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
class NodeTreeIfServiceImpl extends GenericServiceImpl implements NodeTreeIfService {
    private final NodeTreeIfRepository nodeTreeIfRepository;
    private final NodeTreeFactory nodeTreeFactory;
    private final NodeRepository nodeRepository;

    NodeTreeIfServiceImpl(
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeFactory nodeTreeFactory,
            NodeRepository nodeRepository
    ) {
        this.nodeTreeIfRepository = nodeTreeIfRepository;
        this.nodeTreeFactory = nodeTreeFactory;
        this.nodeRepository = nodeRepository;
    }

    public NodeTreeIf create(NodeCreationDTO nodeCreationDTO) throws ServiceException {
        try {
            Node<?> parent = this.nodeRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTreeIf newNode = this.nodeTreeFactory.produceIf(parent);
            return this.read(this.nodeTreeIfRepository.createNode(newNode, parent, getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public NodeTreeIf read(UUID id) throws ServiceException {
        try {
            return this.nodeTreeIfRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a node.", e);
        }
    }

    @Override
    public CompletableFuture<NodeTreeIf> updateAsync(NodeTreeIf entity) throws ServiceException {
        try {
            return this.nodeTreeIfRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a node.", e);
        }
    }

    @Override
    public NodeTreeIf updateSync(NodeTreeIf entity) throws ServiceException {
        try {
            return this.nodeTreeIfRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a node.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.nodeTreeIfRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a node.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.nodeTreeIfRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a node.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeIf>> readAll() throws ServiceException {
        try {
            return this.nodeTreeIfRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeIf>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.nodeTreeIfRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.nodeTreeIfRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.nodeTreeIfRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all nodes by ids.", e);
        }
    }
}
