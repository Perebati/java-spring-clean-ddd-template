package org.gfinnovation.dealsafe.modules.tree.generator.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.exception.models.FailedRequestException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTreeHistory;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.generator.application.service.interfaces.TreeGeneratorService;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeIfCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeGeneratorServiceImpl
 * @since v1.0 (13/02/2025)
 */
@Service
class TreeGeneratorServiceImpl implements TreeGeneratorService {
    private final NodeTreeBlockService nodeTreeBlockService;
    private final NodeTreeIfService nodeTreeIfService;
    private final ComparisonSingularService comparisonSingularService;
    private final ComparisonMultiService comparisonMultiService;
    private final ComparisonCustomListService comparisonCustomListService;
    private final RootTreeDynamicService rootTreeDynamic;
    private final RootTreeStaticService rootTreeStatic;
    private final RootTreeService rootTreeService;
    private final NodeService nodeService;
    public TreeGeneratorServiceImpl(NodeTreeBlockService nodeTreeBlockService,
                                    NodeTreeIfService nodeTreeIfService,
                                    ComparisonSingularService comparisonSingularService,
                                    ComparisonMultiService comparisonMultiService,
                                    ComparisonCustomListService comparisonCustomListService,
                                    RootTreeDynamicService rootTreeDynamic,
                                    RootTreeStaticService rootTreeStatic,
                                    RootTreeService rootTreeService,
                                    NodeService nodeService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
        this.nodeTreeIfService = nodeTreeIfService;
        this.comparisonSingularService = comparisonSingularService;
        this.comparisonMultiService = comparisonMultiService;
        this.comparisonCustomListService = comparisonCustomListService;
        this.rootTreeDynamic = rootTreeDynamic;
        this.rootTreeStatic = rootTreeStatic;
        this.rootTreeService = rootTreeService;
        this.nodeService = nodeService;
    }

    @Transactional
    public RootTree<?> createTree(RootTree<?> root) throws SystemGlobalException {
        try {
            if(root.getNodeType().equals(Node.NodeType.ROOT_STATIC)){
                RootTreeStatic rootTreeStatic = this.rootTreeStatic.create(root.getName(), ((RootTreeStatic) root).getInput_type(), false);
                processNode(rootTreeStatic, root);
                this.rootTreeService.keepHistory(root.getId());
                return this.rootTreeStatic.read(rootTreeStatic.getId());
            } else {
                RootTreeDynamic rootTreeDynamic = this.rootTreeDynamic.create(root.getName(), ((RootTreeDynamic) root).getDynamicInputId(), false);
                processNode(rootTreeDynamic, root);
                this.rootTreeService.keepHistory(root.getId());
                return this.rootTreeDynamic.read(rootTreeDynamic.getId());
            }
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while creating a tree", e);
        }
    }

    /**
     * NOTE: New Nodes will require updates to this method.
     * Via input, this method will create a tree based on the Json in a recursive manner.
     *
     * @param parentEntity Parent entity.
     * @param tree Tree node.
     */
    protected void processNode(Node<?> parentEntity, Node<?> tree) throws SystemGlobalException {
        switch (tree.getNodeType()) {
            case ROOT_STATIC -> {
                for(NodeTree<?> node: ((RootTreeStatic) tree).getNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case ROOT_DYNAMIC -> {
                for(NodeTree<?> node: ((RootTreeDynamic) tree).getNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case NODE_BLOCK -> {
                for(NodeTree<?> node: ((NodeTreeBlock) tree).getNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case NODE_IF -> {
                for(NodeTree<?> node: ((NodeTreeIf) tree).getConditionalNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, NodeTreeIf.SetNode.CONDITIONAL);
                    processNode(childEntity, node);
                }
                for(NodeTree<?> node: ((NodeTreeIf) tree).getThenNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, NodeTreeIf.SetNode.THEN);
                    processNode(childEntity, node);
                }
                for(NodeTree<?> node: ((NodeTreeIf) tree).getElseNodes()){
                    Node<?> childEntity = processChild(parentEntity, node, NodeTreeIf.SetNode.ELSE);
                    processNode(childEntity, node);
                }
            }
        }
    }

    /**
     * Process the child node.
     *
     * @param parent Parent node.
     * @param node Node.
     * @param setNode Set node.
     * @return Node
     */
    private Node<?> processChild(Node<?> parent, Node<?> node, NodeTreeIf.SetNode setNode) throws SystemGlobalException {
        switch (node.getNodeType()){
            case NODE_BLOCK -> {
                return this.nodeTreeBlockService.createBlock(
                        new NodeCreationData(((NodeTreeBlock) node).getName(), parent.getId(), setNode), false
                );
            }
            case NODE_IF -> {
                return this.nodeTreeIfService.createIf(
                        new NodeIfCreationData(parent.getId(), setNode), false
                );
            }
            case CONDITIONAL_COMPARISON_SINGULAR -> {
                return this.comparisonSingularService.createComparison(new ComparisonSingularRecord(
                        ((ComparisonSingular) node).getComparisonTypeEnum(),
                        ((ComparisonSingular) node).getJsonVariablePath(),
                        ((ComparisonSingular) node).getExpectedVar(),
                        parent.getId(),
                        setNode
                ), false);
            }
            case CONDITIONAL_COMPARISON_MULTIPLE -> {
                return this.comparisonMultiService.createComparison(new ComparisonMultiRecord(
                        ((ComparisonMulti) node).getComparisonTypeEnum(),
                        ((ComparisonMulti) node).getJsonVariablePath(),
                        ((ComparisonMulti) node).getExpectedVars(),
                        parent.getId(),
                        setNode
                ), false);
            }
            case CONDITIONAL_COMPARISON_CUSTOM -> {
                return this.comparisonCustomListService.createComparison(new ComparisonCustomListRecord(
                        ((ComparisonCustomList) node).getComparisonTypeEnum(),
                        ((ComparisonCustomList) node).getJsonVariablePath(),
                        ((ComparisonCustomList) node).getCustomListId(),
                        parent.getId(),
                        setNode
                ), false);
            }
            default -> {
                return parent;
            }
        }
    }

    /**
     * NOTE: New Nodes will require updates to this method.
     * Updates an entire tree a keeps tracks of it.
     *
     * @param id   of tree.
     * @param root Whole tree to be updated.
     * @return RootTree<?>
     * @throws SystemGlobalException Standard DealSafe error.
     */
    @Transactional
    public RootTree<?> updateTree(UUID id, RootTree<?> root) throws SystemGlobalException {
        try {
            return switch (root.getNodeType()) {
                case ROOT_STATIC -> updateStaticTree(id, root);
                case ROOT_DYNAMIC -> updateDynamicTree(id, root);
                default -> throw new FailedRequestException(
                        "The tree type of input does not match the tree type in the database", null);
            };
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while updating a tree", e);
        }
    }

    private RootTreeStatic updateStaticTree(UUID id, RootTree<?> root) throws SystemGlobalException {
        RootTreeStatic tree = this.rootTreeStatic.read(id);
        if (!tree.getNodeType().equals(Node.NodeType.ROOT_STATIC)) {
            throw new FailedRequestException(
                    "The tree type of input does not match the tree type in the database", null);
        }
        this.updateTreeData(tree, root);
        this.rootTreeService.keepHistory(tree.getId());
        return this.rootTreeStatic.read(tree.getId());
    }

    private RootTreeDynamic updateDynamicTree(UUID id, RootTree<?> root) throws SystemGlobalException {
        RootTreeDynamic tree = this.rootTreeDynamic.read(id);
        if (!tree.getNodeType().equals(Node.NodeType.ROOT_DYNAMIC)) {
            throw new FailedRequestException(
                    "The tree type of input does not match the tree type in the database", null);
        }
        this.updateTreeData(tree, root);
        this.rootTreeService.keepHistory(tree.getId());
        return this.rootTreeDynamic.read(tree.getId());
    }

    private void updateTreeData(RootTree<?> tree, RootTree<?> root) throws SystemGlobalException {
        tree.updateData(root.getName(), null);
        tree.getNodes().forEach(node -> {
            try {
                this.nodeService.deleteNode(node.getId(), false);
            } catch (SystemGlobalException e) {
                throw new RuntimeException(e);
            }
        });
        processNode(tree, root);
        this.rootTreeService.keepHistory(tree.getId());
    }

    @Transactional
    public RootTree<?> reverseTree(UUID treeId, UUID historyId) throws SystemGlobalException {
        try {
            RootTree<?> baseTree = this.rootTreeService.read(treeId);

            Node.NodeType type = baseTree.getNodeType();
            if (type != Node.NodeType.ROOT_STATIC && type != Node.NodeType.ROOT_DYNAMIC) {
                throw new FailedRequestException(
                        "The tree type of input does not match the tree type in the database", null
                );
            }

            RootTree<?> typedTree = getTypedTree(type, treeId);

            RootTreeHistory history = typedTree.findHistoryById(historyId)
                    .orElseThrow(() -> new FailedRequestException("History not found", null));
            String json = history.getJson();

            if (type == Node.NodeType.ROOT_STATIC) {
                RootTreeStatic rootObject = parseJson(json, RootTreeStatic.class);
                return reverseStaticTree(treeId, rootObject);
            } else {
                RootTreeDynamic rootObject = parseJson(json, RootTreeDynamic.class);
                return reverseDynamicTree(treeId, rootObject);
            }
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while reversing a tree", e);
        }
    }

    private RootTree<?> getTypedTree(Node.NodeType type, UUID treeId) throws SystemGlobalException {
        if (type == Node.NodeType.ROOT_STATIC) {
            return this.rootTreeStatic.read(treeId);
        } else {
            return this.rootTreeDynamic.read(treeId);
        }
    }

    private <T> T parseJson(String json, Class<T> clazz) throws FailedRequestException {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new FailedRequestException("The json is not a valid tree", e);
        }
    }

    private RootTreeStatic reverseStaticTree(UUID treeId, RootTreeStatic rootObject)
            throws SystemGlobalException {

        RootTreeStatic tree = this.rootTreeStatic.read(treeId);

        for (NodeTree<?> node : tree.getNodes()) {
            this.nodeService.deleteNode(node.getId(), false);
        }

        tree.updateData(rootObject.getName(), rootObject.getVersion());
        processNode(tree, rootObject);

        return this.rootTreeStatic.read(tree.getId());
    }

    private RootTreeDynamic reverseDynamicTree(UUID treeId, RootTreeDynamic rootObject)
            throws SystemGlobalException {

        RootTreeDynamic tree = this.rootTreeDynamic.read(treeId);

        for (NodeTree<?> node : tree.getNodes()) {
            this.nodeService.deleteNode(node.getId(), false);
        }

        tree.updateData(rootObject.getName(), rootObject.getVersion());
        processNode(tree, rootObject);

        return this.rootTreeDynamic.read(tree.getId());
    }

}