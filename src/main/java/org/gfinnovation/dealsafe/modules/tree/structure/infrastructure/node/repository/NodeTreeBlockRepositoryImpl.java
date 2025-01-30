package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.repository.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper.NodeTreeBlockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBlockRepositoryImpl
 * @since 30/10/2024
 */

@Component
class NodeTreeBlockRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeBlock, NodeTreeBlockEntity>
        implements NodeTreeBlockRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;

    @Autowired
    NodeTreeBlockRepositoryImpl(
            NodeTreeBlockMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeBlockEntity.class, entityManager), NodeTreeBlockEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
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
    public NodeTreeBlock createNode(NodeTreeBlock newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getNodeType()) {
                case Node.NodeType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    NodeTreeBlock createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Node.NodeType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    NodeTreeBlock createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Node.NodeType.NODE_BLOCK:
                    NodeTreeBlock parent_node = this.read(parent.getId(), auth);
                    NodeTreeBlock createdNodeEntity_2 = this.createSync(newNode, auth);
                    parent_node.addNode(createdNodeEntity_2);
                    this.updateSync(parent_node, auth);
                    return this.read(createdNodeEntity_2.getId(), auth);

                default:
                    throw new RepositoryException("Não é possível salvar um filho no nó: " + parent.getNodeType());
            }
        }catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Ocorreu um erro ao criar um novo nó.", e);
        }
    }
}