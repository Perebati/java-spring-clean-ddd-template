package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.factory.interfaces.NodeTreeRepositoryFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeRepositoryImpl
 * @since 24/01/2025
 */

@Repository
@AllArgsConstructor
class NodeTreeRepositoryImpl
        <T extends NodeTree<JsonNode>>
        implements NodeTreeRepository<T> {
    private final NodeTreeRepositoryFactory nodeRepositoryFactory;
    private final RootTreeStaticRepository rootTreeStaticRepository;
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final NodeTreeBlockRepository nodeTreeBlockRepository;
    private final NodeTreeIfRepository nodeTreeIfRepository;

    @Transactional
    public T createNode(T newNode, Node<?> parent, NodeTreeIf.SetNode nodeSet, RepositoryAuth auth) throws RepositoryException {
        try {
            GenericBusinessRepository<T> nodeRepo = nodeRepositoryFactory.getRepositoryForNode(newNode);

            T createdNodeEntity = nodeRepo.createSync(newNode, auth);

            Node<?> parentEntity = this.readParent(parent, auth, createdNodeEntity);

            if (parentEntity instanceof NodeTreeIf) {
                this.insertInIfNode(createdNodeEntity, (NodeTreeIf) parentEntity, nodeSet);
                this.updateParent(parentEntity, auth);
            } else {
                this.updateParent(parentEntity, auth);

            }
            return nodeRepo.read(createdNodeEntity.getId(), auth);
        } catch (Exception e) {
            throw new RepositoryException("An error has occurred setting up a new node.", e);
        }
    }

    private Node<?> readParent(Node<?> parent, RepositoryAuth auth, T createdEntity) throws RepositoryException {
        switch (parent.getNodeType()) {
            case Node.NodeType.ROOT_STATIC:
                RootTreeStatic parent_static = this.rootTreeStaticRepository.read(parent.getId(), auth);
                parent_static.addNode(createdEntity);
                return parent_static;

            case Node.NodeType.ROOT_DYNAMIC:
                RootTreeDynamic parent_dynamic = this.rootTreeDynamicRepository.read(parent.getId(), auth);
                parent_dynamic.addNode(createdEntity);
                return parent_dynamic;

            case Node.NodeType.NODE_BLOCK:
                NodeTreeBlock parent_block = this.nodeTreeBlockRepository.read(parent.getId(), auth);
                parent_block.addNode(createdEntity);
                return parent_block;

            case Node.NodeType.NODE_IF:
                return this.nodeTreeIfRepository.read(parent.getId(), auth);

            default:
                throw new RepositoryException("Can't read node of type: " + parent.getNodeType());
        }
    }

    private void updateParent(Node<?> parentEntity, RepositoryAuth auth) throws RepositoryException {
        switch (parentEntity.getNodeType()) {
            case Node.NodeType.ROOT_STATIC:
                RootTreeStatic parentStatic = (RootTreeStatic) parentEntity;
                this.rootTreeStaticRepository.updateSync(parentStatic, auth);
                break;

            case Node.NodeType.ROOT_DYNAMIC:
                RootTreeDynamic parentDynamic = (RootTreeDynamic) parentEntity;
                this.rootTreeDynamicRepository.updateSync(parentDynamic, auth);
                break;

            case Node.NodeType.NODE_BLOCK:
                NodeTreeBlock parentBlock = (NodeTreeBlock) parentEntity;
                this.nodeTreeBlockRepository.updateSync(parentBlock, auth);
                break;

            case Node.NodeType.NODE_IF:
                NodeTreeIf parentIf = (NodeTreeIf) parentEntity;
                this.nodeTreeIfRepository.updateSync(parentIf, auth);
                break;

            default:
                throw new RepositoryException("Can't update a node of type: " + parentEntity.getNodeType());
        }
    }

    private void insertInIfNode(
            T createdNodeEntity,
            NodeTreeIf parent,
            NodeTreeIf.SetNode position
    ) throws RepositoryException {
        try {
            switch (position) {
                case NodeTreeIf.SetNode.CONDITIONAL:
                    parent.addConditionalNode(createdNodeEntity);
                    break;
                case NodeTreeIf.SetNode.THEN:
                case null:
                    parent.addThenNode(createdNodeEntity);
                    break;
                case NodeTreeIf.SetNode.ELSE:
                    parent.addElseNode(createdNodeEntity);
                    break;
                default:
                    break;
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("An error has occurred when inserting a node inside an if node.", e);
        }
    }
}