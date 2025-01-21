package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.Branch;
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
     * @param parent    Parent of given newNode, can be either a root or a node, always a branch.
     * @return NodeTree
     * @throws RepositoryException Thrown when an error occur on Repository Level
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Transactional
    @Override
    public NodeTree createNode(NodeTree newNode, Branch parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getBranchType()){
                case Branch.BranchType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    NodeTree createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Branch.BranchType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    NodeTree createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Branch.BranchType.NODE_COMMON:
                    NodeTree parent_node = this.read(parent.getId(), auth);
                    NodeTree createdNodeEntity_2 = this.createSync(newNode, auth);
                    parent_node.addNode(createdNodeEntity_2);
                    this.updateSync(parent_node, auth);
                    return this.read(createdNodeEntity_2.getId(), auth);

                default:
                    throw new RepositoryException("Tipo de branch desconhecido: ");
            }
        } catch (Exception e) {
            throw new RepositoryException("Ocorreu um erro ao criar um novo nó.", e);
        }
    }
}