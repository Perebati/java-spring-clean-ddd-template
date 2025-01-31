package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeActionRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeActionRepositoryImpl
 * @since 22/01/2025
 */

@Repository
class NodeTreeActionRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeAction, NodeTreeActionEntity>
        implements NodeTreeActionRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;
    private final NodeTreeBlockRepository nodeTreeBlockRepository;

    @Autowired
    NodeTreeActionRepositoryImpl(
            NodeTreeActionMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository,
            NodeTreeBlockRepository nodeTreeBlockRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeActionEntity.class, entityManager), NodeTreeActionEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
    }

    /**
     * Transactional operation to save a new node on the database.
     *
     * @param newNode New entity to be saved, mede by factory.
     * @param parent  Parent of given newNode, can be either a root or a node, always a branch.
     * @return NodeTree
     * @throws RepositoryException Thrown when an error occur on Repository Level
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Transactional
    @Override
    public NodeTreeAction createNode(NodeTreeAction newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getNodeType()) {
                case Node.NodeType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    NodeTreeAction createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Node.NodeType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    NodeTreeAction createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Node.NodeType.NODE_BLOCK:
                    NodeTreeBlock parent_node = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                    NodeTreeAction createdNodeEntity_2 = this.createSync(newNode, auth);
                    parent_node.addNode(createdNodeEntity_2);
                    this.nodeTreeBlockRepository.updateSync(parent_node, auth);
                    return this.read(createdNodeEntity_2.getId(), auth);

                default:
                    throw new RepositoryException("Tipo de branch desconhecido: ");
            }
        } catch (Exception e) {
            throw new RepositoryException("Ocorreu um erro ao criar um novo nó.", e);
        }
    }
}