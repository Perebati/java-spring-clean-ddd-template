package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.repository.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.repository.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.repository.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.repository.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.mapper.NodeTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeRepositoryImpl
 * @since 30/10/2024
 */

@Component
class NodeTreeRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTree, NodeTreeEntity>
        implements NodeTreeRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;

    @Autowired
    NodeTreeRepositoryImpl(
            NodeTreeMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeEntity.class, entityManager), NodeTreeEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
    }

    /**
     * Transactional operation to save a new node on the database.
     *
     * @param newNode   New entity to be saved, mede by factory.
     * @param parent    Parent of given newNode, can be either a root or a node.
     * @param parent_id ParentId of given newNode
     * @return NodeTree
     * @throws RepositoryException Thrown when an error occur on Repository Level
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Transactional
    @Override
    public NodeTree createNode(@NotNull NodeTree newNode, @NotNull Object parent, @NotNull UUID parent_id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            if (parent instanceof RootTreeStatic) {
                RootTreeStatic parent_root = this.rootTreeStaticRepository.read(parent_id, auth);
                NodeTree createdNodeEntity = this.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.rootTreeRepository.updateGenericRootSync(parent_root, auth);
                return this.read(createdNodeEntity.getId(), auth);
            } else if (parent instanceof RootTreeDynamic) {
                RootTreeDynamic parent_root = this.rootTreeDynamicRepository.read(parent_id, auth);
                NodeTree createdNodeEntity = this.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.rootTreeRepository.updateGenericRootSync(parent_root, auth);
                return this.read(createdNodeEntity.getId(), auth);
            } else {
                NodeTree parent_node = this.read(parent_id, auth);
                NodeTree createdNodeEntity = this.createSync(newNode, auth);
                parent_node.addNode(createdNodeEntity);
                this.updateSync(parent_node, auth);
                return this.read(createdNodeEntity.getId(), auth);
            }
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong creating a new node.", e);
        }
    }
}