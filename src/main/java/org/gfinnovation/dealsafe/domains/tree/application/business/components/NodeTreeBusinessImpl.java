package org.gfinnovation.dealsafe.domains.tree.application.business.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.NodeTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.RootTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
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
class NodeTreeBusinessImpl extends GenericBusinessImpl implements NodeTreeBusiness {
    private final TreeRepository treeRepository;
    private final TreeFactory treeFactory;
    private final RootTreeBusiness rootTreeBusiness;

    @Autowired
    public NodeTreeBusinessImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, TreeRepository treeRepository, TreeFactory treeFactory, RootTreeBusiness rootTreeBusiness) {
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
     * @throws BusinessException       Thrown when an error occurs on business level.
     * @throws FactoryException        Thrown when an error occurs on factory level.
     * @throws RepositoryException     Thrown when an error occurs on repository level.
     * @throws ValidationException     Thrown when an error occurs on factory level.
     * @throws EntityNotFoundException Thrown when an entity is not found by id.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public NodeTreeEntity create(String name, Integer sequence, UUID parent_id) throws BusinessException, FactoryException, RepositoryException, ValidationException, EntityNotFoundException {
        try {
            Object parent = this.rootTreeBusiness.readGenericRoot(parent_id);
            NodeTreeEntity newNode = this.treeFactory.getNodeTreeFactory().produce(getUserId(), getCompanyId(), name, sequence);
            return this.treeRepository.createNode(getUserId(), getCompanyId(), newNode, parent, parent_id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public NodeTreeEntity read(UUID id) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().read(getUserId(), getCompanyId(), id);
    }

    @Override
    public CompletableFuture<NodeTreeEntity> updateAsync(NodeTreeEntity entity) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().updateAsync(getUserId(), getCompanyId(), entity);
    }

    public NodeTreeEntity updateSync(NodeTreeEntity entity) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().updateSync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public void deleteAsync(UUID id) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().deleteAsync(getUserId(), getCompanyId(), id);
    }

    @Override
    public void deleteSync(UUID id) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().deleteSync(getUserId(), getCompanyId(), id);
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAll() throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().findAll(getUserId(), getCompanyId());
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().findAllByIds(getUserId(), getCompanyId(), ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().check(getUserId(), getCompanyId(), id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().checkAll(getUserId(), getCompanyId(), ids);
    }
}
