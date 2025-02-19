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
                RootTreeStatic rootTreeStatic = this.rootTreeStatic.create(root.getName(), ((RootTreeStatic) root).getInput_type());
                processNode(rootTreeStatic, root);

                return this.rootTreeStatic.read(rootTreeStatic.getId());
            } else {
                RootTreeDynamic rootTreeDynamic = this.rootTreeDynamic.create(root.getName(), ((RootTreeDynamic) root).getDynamicInputId());
                processNode(rootTreeDynamic, root);

                return this.rootTreeDynamic.read(rootTreeDynamic.getId());
            }
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while creating a tree", e);
        }
    }

    /**
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
     * @param parent Parent node.
     * @param node Node.
     * @param setNode Set node.
     * @return Node
     */
    protected Node<?> processChild(Node<?> parent, Node<?> node, NodeTreeIf.SetNode setNode) throws SystemGlobalException {
        switch (node.getNodeType()){
            case NODE_BLOCK -> {
                return this.nodeTreeBlockService.createBlock(
                        new NodeCreationData(((NodeTreeBlock) node).getName(), parent.getId(), setNode)
                );
            }
            case NODE_IF -> {
                return this.nodeTreeIfService.createIf(
                        new NodeIfCreationData(parent.getId(), setNode)
                );
            }
            case CONDITIONAL_COMPARISON_SINGULAR -> {
                return this.comparisonSingularService.create(new ComparisonSingularRecord(
                        ((ComparisonSingular) node).getComparisonTypeEnum(),
                        ((ComparisonSingular) node).getJsonVariablePath(),
                        ((ComparisonSingular) node).getExpectedVar(),
                        parent.getId(),
                        setNode
                ));
            }
            case CONDITIONAL_COMPARISON_MULTIPLE -> {
                return this.comparisonMultiService.create(new ComparisonMultiRecord(
                        ((ComparisonMulti) node).getComparisonTypeEnum(),
                        ((ComparisonMulti) node).getJsonVariablePath(),
                        ((ComparisonMulti) node).getExpectedVars(),
                        parent.getId(),
                        setNode
                ));
            }
            case CONDITIONAL_COMPARISON_CUSTOM -> {
                return this.comparisonCustomListService.create(
                        ((ComparisonCustomList) node).getComparisonTypeEnum(),
                        ((ComparisonCustomList) node).getJsonVariablePath(),
                        ((ComparisonCustomList) node).getCustomListId(),
                        parent.getId(),
                        setNode
                );
            }
            default -> {
                return parent;
            }
        }
    }

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
        return this.rootTreeStatic.read(tree.getId());
    }

    private RootTreeDynamic updateDynamicTree(UUID id, RootTree<?> root) throws SystemGlobalException {
        RootTreeDynamic tree = this.rootTreeDynamic.read(id);
        if (!tree.getNodeType().equals(Node.NodeType.ROOT_DYNAMIC)) {
            throw new FailedRequestException(
                    "The tree type of input does not match the tree type in the database", null);
        }
        this.updateTreeData(tree, root);
        return this.rootTreeDynamic.read(tree.getId());
    }

    private void updateTreeData(RootTree<?> tree, RootTree<?> root) throws SystemGlobalException {
        tree.updateData(root.getName(), null);
        tree.getNodes().forEach(node -> {
            try {
                this.nodeService.deleteNode(node.getId());
            } catch (SystemGlobalException e) {
                throw new RuntimeException(e);
            }
        });
        processNode(tree, root);
        this.rootTreeService.addHistoryToTree(tree.getId(), tree);
    }

    @Transactional
    public RootTree<?> reverseTree(UUID treeId, UUID historyId) throws SystemGlobalException {
        try {
            RootTree<?> baseTree = this.rootTreeStatic.read(treeId);
            RootTreeHistory history = baseTree.findHistoryById(historyId)
                    .orElseThrow(() -> new FailedRequestException("History not found", null));
            String json = history.getJson();

            ObjectMapper mapper = new ObjectMapper();
            RootTree<?> rootObject;
            try {
                rootObject = mapper.readValue(json, RootTree.class);
            } catch (Exception e) {
                throw new FailedRequestException("The json is not a valid tree", e);
            }

            return switch (baseTree.getNodeType()) {
                case ROOT_STATIC -> reverseStaticTree(treeId, rootObject);
                case ROOT_DYNAMIC -> reverseDynamicTree(treeId, rootObject);
                default -> throw new FailedRequestException(
                        "The tree type of input does not match the tree type in the database", null);
            };
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while reversing a tree", e);
        }
    }

    private RootTreeStatic reverseStaticTree(UUID treeId, RootTree<?> rootObject) throws SystemGlobalException {
        RootTreeStatic tree = this.rootTreeStatic.read(treeId);
        tree.updateData(rootObject.getName(), rootObject.getVersion());
        processNode(tree, rootObject);
        return this.rootTreeStatic.read(tree.getId());
    }

    private RootTreeDynamic reverseDynamicTree(UUID treeId, RootTree<?> rootObject) throws SystemGlobalException {
        RootTreeDynamic tree = this.rootTreeDynamic.read(treeId);
        tree.updateData(rootObject.getName(), rootObject.getVersion());
        processNode(tree, rootObject);
        return this.rootTreeDynamic.read(tree.getId());
    }
}