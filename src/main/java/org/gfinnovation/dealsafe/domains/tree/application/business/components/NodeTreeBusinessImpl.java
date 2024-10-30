package org.gfinnovation.dealsafe.domains.tree.application.business.components;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.configuration.exception.models.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.RepositoryException;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.NodeTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.RootTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusinessImpl
 * @authorNote Handles the creation of Nodes in the validation tree.
 * Every node has a parent, that being either a Root or another Node.
 * Each node can have linked operations.
 * @since 30/10/2024
 */
@Component
@RequiredArgsConstructor
class NodeTreeBusinessImpl implements NodeTreeBusiness {
    private final TreeRepository treeRepository;
    private final TreeFactory treeFactory;
    private final RootTreeBusiness rootTreeBusiness;

    @Override
    @Transactional
    public NodeTreeEntity create(UUID user_id, UUID company_id, String name, Integer sequence, UUID parent_id) throws RuntimeException {
        try {
            Object parent = this.rootTreeBusiness.readGenericRoot(parent_id);
            if (parent instanceof RootTreeStaticEntity) {
                RootTreeStaticEntity parent_root = this.rootTreeBusiness.readRootStatic(parent_id).orElseThrow(() ->
                        new EntityNotFoundException("Parent of node not found!"));
                NodeTreeEntity createdNodeEntity = this.treeRepository.getNodeTreeRepository().create(treeFactory.getNodeTreeFactory().createNode(user_id, company_id, name, sequence));
                this.rootTreeBusiness.update(parent_root.addNode(createdNodeEntity));

                return createdNodeEntity;
            } else if (parent instanceof RootTreeDynamicEntity) {
                RootTreeDynamicEntity parent_root = this.rootTreeBusiness.readRootDynamic(parent_id).orElseThrow(() ->
                        new EntityNotFoundException("Parent of node not found!"));

                NodeTreeEntity createdNodeEntity = this.treeRepository.getNodeTreeRepository().create(treeFactory.getNodeTreeFactory().createNode(user_id, company_id, name, sequence));
                this.rootTreeBusiness.update(parent_root.addNode(createdNodeEntity));

                return createdNodeEntity;
            } else {
                NodeTreeEntity parent_node = this.read(parent_id).orElseThrow();
                NodeTreeEntity createdNodeEntity = this.treeRepository.getNodeTreeRepository().create(treeFactory.getNodeTreeFactory().createNode(user_id, company_id, name, sequence));
                this.update(parent_node.addChild(createdNodeEntity));

                return createdNodeEntity;
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Optional<NodeTreeEntity> read(UUID id) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().read(id);
    }

    @Override
    public NodeTreeEntity update(NodeTreeEntity entity) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().update(entity);
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().delete(id);
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAll() throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().findAll();
    }

    @Override
    public Optional<List<NodeTreeEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.treeRepository.getNodeTreeRepository().findAllByIds(ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.treeRepository.getNodeTreeRepository().checkAll(ids);
    }
}
