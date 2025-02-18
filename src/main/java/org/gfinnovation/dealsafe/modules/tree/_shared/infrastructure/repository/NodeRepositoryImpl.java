package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.mapper.NodeMapper;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeRepositoryImpl
 * @since v1.0 (06/02/2025)
 */
@Repository
class NodeRepositoryImpl
        extends GenericBusinessRepositoryImpl<Node<NodeInput>, NodeEntity>
        implements NodeRepository {

    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final NodeTreeBlockRepository nodeTreeBlockRepository;
    private final NodeTreeIfRepository nodeTreeIfRepository;
    private final NodeTreeRepository<?> nodeTreeRepository;

    @Autowired
    NodeRepositoryImpl(
            NodeMapper mapper,
            EntityManager entityManager,
            RootTreeStaticRepository rootTreeStaticRepository,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            NodeTreeBlockRepository nodeTreeBlockRepository,
            NodeTreeIfRepository nodeTreeIfRepository,
            NodeTreeRepository<?> nodeTreeRepository) {
        super(mapper, new SimpleJpaRepository<>(NodeEntity.class, entityManager), NodeEntity.class);
        this.rootTreeStaticRepository = rootTreeStaticRepository;
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.nodeTreeBlockRepository = nodeTreeBlockRepository;
        this.nodeTreeIfRepository = nodeTreeIfRepository;
        this.nodeTreeRepository = nodeTreeRepository;
    }

    @Transactional
    public void deleteNode(UUID id, RepositoryAuth auth) throws SystemGlobalException {
        Node<?> nodeTarget = this.read(id, auth);
        switch (nodeTarget.getNodeType()) {
            case ROOT_STATIC -> {
                RootTreeStatic root = this.rootTreeStaticRepository.read(id, auth);
                deleteChildren(root.getNodes(), auth);
                this.rootTreeStaticRepository.delete(id, auth);
            }
            case ROOT_DYNAMIC -> {
                RootTreeDynamic root = this.rootTreeDynamicRepository.read(id, auth);
                deleteChildren(root.getNodes(), auth);
                this.rootTreeDynamicRepository.delete(id, auth);
            }
            default -> {
                NodeTree<NodeInput> nodeTree = this.nodeTreeRepository.read(id, auth);
                Node<?> parent = this.read(nodeTree.getParentId(), auth);
                deleteNodeRecursive(id, auth);
                removeChildFromParent(parent, id, auth);
            }
        }
    }

    @Transactional
    protected void deleteChildren(List<NodeTree<NodeInput>> children, RepositoryAuth auth) throws SystemGlobalException {
        for (NodeTree<NodeInput> child : children) {
            deleteNodeRecursive(child.getId(), auth);
        }
    }

    @Transactional
    protected void deleteNodeRecursive(UUID id, RepositoryAuth auth) throws SystemGlobalException {
        if (id == null) {
            return;
        }

        NodeTree<NodeInput> nodeTree = this.nodeTreeRepository.read(id, auth);
        List<NodeTree<NodeInput>> children = getChildNodes(nodeTree, auth);
        for (NodeTree<NodeInput> child : children) {
            deleteNodeRecursive(child.getId(), auth);
        }
        this.delete(id, auth);
    }

    private List<NodeTree<NodeInput>> getChildNodes(NodeTree<NodeInput> nodeTree, RepositoryAuth auth)
            throws SystemGlobalException {
        return switch (nodeTree.getNodeType()) {
            case NODE_IF -> {
                NodeTreeIf nodeIf = this.nodeTreeIfRepository.read(nodeTree.getId(), auth);
                List<NodeTree<NodeInput>> nodes = new ArrayList<>();
                nodes.addAll(nodeIf.getConditionalNodes());
                nodes.addAll(nodeIf.getThenNodes());
                nodes.addAll(nodeIf.getElseNodes());
                yield nodes;
            }
            case NODE_BLOCK -> {
                NodeTreeBlock nodeBlock = this.nodeTreeBlockRepository.read(nodeTree.getId(), auth);
                yield new ArrayList<>(nodeBlock.getNodes());
            }
            default -> Collections.emptyList();
        };
    }

    private void removeChildFromParent(Node<?> parent, UUID childId, RepositoryAuth auth)
            throws SystemGlobalException {
        switch (parent.getNodeType()) {
            case NODE_IF -> {
                NodeTreeIf parentIf = this.nodeTreeIfRepository.read(parent.getId(), auth);
                parentIf.removeConditionalNode(childId);
                parentIf.removeThenNode(childId);
                parentIf.removeElseNode(childId);
                this.nodeTreeIfRepository.update(parentIf, auth);
            }
            case NODE_BLOCK -> {
                NodeTreeBlock parentBlock = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                parentBlock.removeNode(childId);
                this.nodeTreeBlockRepository.update(parentBlock, auth);
            }
            case ROOT_STATIC -> {
                RootTreeStatic parentStatic = this.rootTreeStaticRepository.read(parent.getId(), auth);
                parentStatic.removeNode(childId);
                this.rootTreeStaticRepository.update(parentStatic, auth);
            }
            case ROOT_DYNAMIC -> {
                RootTreeDynamic parentDynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                parentDynamic.removeNode(childId);
                this.rootTreeDynamicRepository.update(parentDynamic, auth);
            }
            default -> {
                throw new SystemGlobalException("Can't remove child from parent of type: " + parent.getNodeType(), null);
            }
        }
    }
}