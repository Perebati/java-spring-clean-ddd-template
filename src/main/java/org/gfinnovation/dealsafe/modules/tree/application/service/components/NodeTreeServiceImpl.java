package org.gfinnovation.dealsafe.modules.tree.application.service.components;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
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
final class NodeTreeServiceImpl extends GenericServiceImpl implements NodeTreeService {
    private final TreeRepository treeRepository;
    private final TreeFactory treeFactory;
    private final RootTreeService rootTreeBusiness;

    @Autowired
    public NodeTreeServiceImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, TreeRepository treeRepository, TreeFactory treeFactory, RootTreeService rootTreeBusiness) {
        super(userBusiness, companyBusiness);
        this.treeRepository = treeRepository;
        this.treeFactory = treeFactory;
        this.rootTreeBusiness = rootTreeBusiness;
    }

    /**
     * Handles the creation of a Node.
     * Every Node needs a parent, in this case the parent can be either an RootNode or a common Node.
     * ParentId can either belong to a Root or a Node, this method supports both.
     *
     * @param name      Name of the new node.
     * @param sequence  Position/Priority of node execution.
     * @param parent_id ParentId, tha can be either an id from a RootNode or another Node.
     * @return NodeTreeEntity
     * @throws BusinessException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public NodeTreeEntity create(String name, Integer sequence, UUID parent_id) throws BusinessException {
        try {
            Object parent = this.rootTreeBusiness.readGenericRoot(parent_id);
            NodeTreeEntity newNode = this.treeFactory.getNodeTreeFactory().produce(name, sequence);
            return this.treeRepository.createNode(newNode, parent, parent_id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public NodeTreeEntity read(UUID id) throws BusinessException {
        try {
            return this.treeRepository.getNodeTreeRepository().read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a node.", e);
        }
    }

    @Override
    public CompletableFuture<NodeTreeEntity> updateAsync(NodeTreeEntity entity) throws BusinessException {
        try {
            return this.treeRepository.getNodeTreeRepository().updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating a node.", e);
        }
    }

    @Override
    public NodeTreeEntity updateSync(NodeTreeEntity entity) throws BusinessException {
        try {
            return this.treeRepository.getNodeTreeRepository().updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating a node.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getNodeTreeRepository().deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting a node.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.treeRepository.getNodeTreeRepository().deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting a node.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAll() throws BusinessException {
        try {
            return this.treeRepository.getNodeTreeRepository().findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all nodes.", e);
        }
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.treeRepository.getNodeTreeRepository().findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all nodes by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.treeRepository.getNodeTreeRepository().check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a node.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.treeRepository.getNodeTreeRepository().checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all nodes by ids.", e);
        }
    }
}
