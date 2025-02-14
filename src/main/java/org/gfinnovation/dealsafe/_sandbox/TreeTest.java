package org.gfinnovation.dealsafe._sandbox;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.engine.generator.application.service.interfaces.TreeGeneratorService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
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
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.slf4j.MDC;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeTest
 * @since v1.0 (30/11/2024)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("teste")
@Tag(name = "Teste")
@Profile("dev")
public class TreeTest {
    private final NodeTreeBlockService nodeTreeBlockService;
    private final NodeTreeIfService nodeTreeIfService;
    private final ComparisonSingularService comparisonSingularService;
    private final ComparisonMultiService comparisonMultiService;
    private final ComparisonCustomListService comparisonCustomListService;
    private final CompanyListService companyListService;
    private final InputService inputService;
    private final RootTreeDynamicService rootTreeDynamic;
    private final RootTreeStaticService rootTreeStatic;
    private final TreeGeneratorService treeGeneratorService;

//    @PostMapping("tree2")
//    public boolean testeTree2() throws RuntimeException, JsonProcessingException {
//        try {
//
//            String json = """
//                {
//                  "nome": "João",
//                  "idade": 30,
//                  "CPF": 11330176650,
//                  "endereco": {
//                    "rua": "Rua A",
//                    "bairro": "Centro"
//                  },
//                  "telefone": ["123456789", "987654321"],
//                  "teste": {
//                      "teste": {
//                          "teste": "teste"
//                      }
//                   }
//                }
//                """;
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            JsonNode rootNode = mapper.readTree(json);
//
//            MDC.put("userId", UUID.randomUUID().toString());
//            MDC.put("whitelabelId", UUID.randomUUID().toString());
//            MDC.put("requestId", UUID.randomUUID().toString());
//
//            Input input = this.inputService.createInput(new CreateInputData("Example", rootNode));
//
//            RootTreeDynamic createdRoot = this.rootTreeDynamic.create("ROOT Teste", input.getId());
//
//            NodeTree<?> node1 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 1", createdRoot.getId(), null));
//
//            NodeTree<?> node2 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 3", createdRoot.getId(), null));
//
//            NodeTree<?> createdNode2 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 2", node1.getId(), null));
//
//            NodeTree<?> nodeIf1 = this.nodeTreeIfService.createIf(new NodeCreationData("NODE IF 1", node1.getId(), null));
//
//            NodeTree<?> nodeIf2 = this.nodeTreeIfService.createIf(new NodeCreationData("NODE IF 2", nodeIf1.getId(), NodeTreeIf.SetNode.CONDITIONAL));
//
//            NodeTree<?> node4 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 5", nodeIf1.getId(), NodeTreeIf.SetNode.THEN));
//
//            NodeTree<?> node5 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 6", nodeIf1.getId(), NodeTreeIf.SetNode.ELSE));
//
//            NodeTree<?> nodeIf3 = this.nodeTreeIfService.createIf(new NodeCreationData("NODE IF 3", nodeIf1.getId(), null));
//
//            ComparisonSingular comparisonSingular = this.comparisonSingularService.create(new ComparisonSingularRecord(
//                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
//                    "endereco$bairro@",
//                    "Centro",
//                    nodeIf3.getParentId(),
//                    NodeTreeIf.SetNode.CONDITIONAL
//            ));
//
//            ComparisonSingular comparisonSingular2 = this.comparisonSingularService.create(new ComparisonSingularRecord(
//                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
//                    ".endereco*bairro/",
//                    "Centro",
//                    nodeIf3.getParentId(),
//                    NodeTreeIf.SetNode.CONDITIONAL
//            ));
//
//            ComparisonSingular comparisonSingular3 = this.comparisonSingularService.create(new ComparisonSingularRecord(
//                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
//                    "/endereco.bairro$",
//                    "Centro",
//                    nodeIf3.getParentId(),
//                    NodeTreeIf.SetNode.CONDITIONAL
//            ));
//
//            ComparisonMulti comparisonMulti = this.comparisonMultiService.create(new ComparisonMultiRecord(
//                    ComparisonMulti.ComparisonMultiTypeEnum.NOT_CONTAINS,
//                    ".CPF/",
//                    List.of("11330176650"),
//                    nodeIf3.getParentId(),
//                    NodeTreeIf.SetNode.CONDITIONAL
//            ));
//
//            CompanyList companyList = this.companyListService.createCompanyList(
//                    "Dealboard",
//                    List.of("11330176650"));
//
//            ComparisonCustomList comparisonCustomList = this.comparisonCustomListService.create(
//                    ComparisonCustomList.ComparisonCustomListEnum.NOT_CONTAINS,
//                    "/CPF",
//                    companyList.getId(),
//                    nodeIf3.getParentId(),
//                    NodeTreeIf.SetNode.ELSE
//            );
//            JsonNode jsonNode = mapper.readTree(json);
//
//
//            createdRoot = this.rootTreeDynamic.read(createdRoot.getId());
//
//            //createdRoot.traverse(jsonNode);
//
//            throw new AdapterException("sxfsdf");
//        } catch (SystemGlobalException e) {
//            throw e;
//        }
//    }

    /*

    Test case:

        {
          "input_type": "TESTE",
          "name": "Root Tree Name",
          "nodes": [
            {
              "name": "First Block",
              "nodes": [
                {
                  "conditionalNodes": [
                    {
                      "name": "Nested Block in conditionalNodes",
                      "nodes": []
                    }
                  ],
                  "thenNodes": [
                    {
                      "comparisonTypeEnum": "CONTAINS",
                      "jsonVariablePath": "/idade",
                      "customListId": "2e058ea4-6f1a-4dc8-9fdc-bbda55f7ed80"
                    }
                  ],
                  "elseNodes": [
                    {
                      "name": "Else Block Node",
                      "nodes": []
                    }
                  ]
                },
                {
                  "comparisonTypeEnum": "CONTAINS",
                  "jsonVariablePath": "/idade",
                  "customListId": "9c1508f0-3baf-4682-91f1-4341e77e2af3"
                }
              ]
            }
          ]
        }

     */

    @PostMapping("tree1234")
    public ResponseEntity<RootTree<?>> createTree(@RequestBody RootTree<?> root) {
        MDC.put("userId", UUID.randomUUID().toString());
        MDC.put("whitelabelId", UUID.randomUUID().toString());
        MDC.put("requestId", UUID.randomUUID().toString());
        try {
            RootTree<?> rootResponse = this.treeGeneratorService.createTree(root);
            return new ResponseEntity<>(rootResponse, HttpStatus.CREATED);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("An error occurred while creating a tree");
        }
    }

    @Transactional
    protected void processNode(Node<?> parentEntity, Node<?> tree) {
        switch (tree.getNodeType()) {
            case ROOT_STATIC -> {
                for (NodeTree<?> node : ((RootTreeStatic) tree).getNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case ROOT_DYNAMIC -> {
                for (NodeTree<?> node : ((RootTreeDynamic) tree).getNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case NODE_BLOCK -> {
                for (NodeTree<?> node : ((NodeTreeBlock) tree).getNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, null);
                    processNode(childEntity, node);
                }
            }
            case NODE_IF -> {
                for (NodeTree<?> node : ((NodeTreeIf) tree).getConditionalNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, NodeTreeIf.SetNode.CONDITIONAL);
                    processNode(childEntity, node);
                }
                for (NodeTree<?> node : ((NodeTreeIf) tree).getThenNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, NodeTreeIf.SetNode.THEN);
                    processNode(childEntity, node);
                }
                for (NodeTree<?> node : ((NodeTreeIf) tree).getElseNodes()) {
                    Node<?> childEntity = processNode2(parentEntity, node, NodeTreeIf.SetNode.ELSE);
                    processNode(childEntity, node);
                }
            }
        }
    }

    protected Node<?> processNode2(Node<?> parent, Node<?> node, NodeTreeIf.SetNode setNode) {
        switch (node.getNodeType()) {
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