package org.gfinnovation.dealsafe.modules.tree.branch.domain.node.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.branch.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.BranchRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.factory.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.repository.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.service.interfaces.NodeTreeService;
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
class NodeTreeServiceImpl extends GenericServiceImpl implements NodeTreeService {
    private final NodeTreeRepository nodeTreeRepository;
    private final NodeTreeFactory nodeTreeFactory;
    private final BranchRepository branchRepository;

    @Autowired
    public NodeTreeServiceImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            NodeTreeRepository nodeTreeRepository,
            NodeTreeFactory nodeTreeFactory,
            BranchRepository branchRepository) {
        super(userBusiness, companyBusiness);
        this.nodeTreeRepository = nodeTreeRepository;
        this.nodeTreeFactory = nodeTreeFactory;
        this.branchRepository = branchRepository;
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

    public NodeTree create(NodeCreationDTO nodeCreationDTO) throws ServiceException {
        try {
            Branch parent = this.branchRepository.read(nodeCreationDTO.parent_id(), getRepositoryAuth());
            NodeTree newNode = this.nodeTreeFactory.produce(nodeCreationDTO.name(), parent);
            return this.read(this.nodeTreeRepository.createNode(newNode, parent, getRepositoryAuth()).getId());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public NodeTree read(UUID id) throws ServiceException {
        try {
            return this.nodeTreeRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a node.", e);
        }
    }

    @Override
    public CompletableFuture<NodeTree> updateAsync(NodeTree entity) throws ServiceException {
        try {
            return this.nodeTreeRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a node.", e);
        }
    }

    @Override
    public NodeTree updateSync(NodeTree entity) throws ServiceException {
        try {
            return this.nodeTreeRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a node.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.nodeTreeRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a node.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.nodeTreeRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a node.", e);
        }
    }

    @Override
    public Optional<List<NodeTree>> readAll() throws ServiceException {
        try {
            return this.nodeTreeRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes.", e);
        }
    }

    @Override
    public Optional<List<NodeTree>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.nodeTreeRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all nodes by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.nodeTreeRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.nodeTreeRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all nodes by ids.", e);
        }
    }
}
