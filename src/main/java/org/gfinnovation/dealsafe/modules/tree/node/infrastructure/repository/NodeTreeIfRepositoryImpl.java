package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeIfEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeIfMapper;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
class NodeTreeIfRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeIf, NodeTreeIfEntity>
        implements NodeTreeIfRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeRepository rootTreeRepository;

    /*
    TODO: Verificar dependencia circular.
     */
    @Lazy
    @Autowired
    private NodeTreeBlockRepository nodeTreeBlockRepository;

    @Autowired
    NodeTreeIfRepositoryImpl(
            NodeTreeIfMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeRepository rootTreeRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeIfEntity.class, entityManager), NodeTreeIfEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeRepository = rootTreeRepository;
    }

    @Transactional
    @Override
    public NodeTreeIf createNode(NodeTreeIf newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException {
        try {
            switch (parent.getNodeType()) {
                case Node.NodeType.ROOT_STATIC:
                    RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                    NodeTreeIf createdNodeEntity_0 = this.createSync(newNode, auth);
                    parent_static.addNode(createdNodeEntity_0);
                    this.rootTreeRepository.updateGenericRootSync(parent_static, auth);
                    return this.read(createdNodeEntity_0.getId(), auth);

                case Node.NodeType.ROOT_DYNAMIC:
                    RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                    NodeTreeIf createdNodeEntity_1 = this.createSync(newNode, auth);
                    parent_dynamic.addNode(createdNodeEntity_1);
                    this.rootTreeRepository.updateGenericRootSync(parent_dynamic, auth);
                    return this.read(createdNodeEntity_1.getId(), auth);

                case Node.NodeType.NODE_BLOCK:
                    NodeTreeBlock parent_node = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                    NodeTreeIf createdNodeEntity_2 = this.createSync(newNode, auth);
                    parent_node.addNode(createdNodeEntity_2);
                    this.nodeTreeBlockRepository.updateSync(parent_node, auth);
                    return this.read(createdNodeEntity_2.getId(), auth);

                default:
                    throw new RepositoryException("Não é possível salvar um filho no nó: " + parent.getNodeType());
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Ocorreu um erro ao criar um novo nó.", e);
        }
    }
}
