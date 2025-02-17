package org.gfinnovation.dealsafe.modules.engine.generator.application.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.engine.generator.application.service.interfaces.TreeGeneratorService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
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

    public TreeGeneratorServiceImpl(NodeTreeBlockService nodeTreeBlockService,
                                    NodeTreeIfService nodeTreeIfService,
                                    ComparisonSingularService comparisonSingularService,
                                    ComparisonMultiService comparisonMultiService,
                                    ComparisonCustomListService comparisonCustomListService,
                                    RootTreeDynamicService rootTreeDynamic,
                                    RootTreeStaticService rootTreeStatic) {
        this.nodeTreeBlockService = nodeTreeBlockService;
        this.nodeTreeIfService = nodeTreeIfService;
        this.comparisonSingularService = comparisonSingularService;
        this.comparisonMultiService = comparisonMultiService;
        this.comparisonCustomListService = comparisonCustomListService;
        this.rootTreeDynamic = rootTreeDynamic;
        this.rootTreeStatic = rootTreeStatic;
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
}