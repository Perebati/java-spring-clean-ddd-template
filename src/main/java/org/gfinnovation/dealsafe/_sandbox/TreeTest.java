package org.gfinnovation.dealsafe._sandbox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
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
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.slf4j.MDC;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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


    @PostMapping("tree")
    public boolean testeTree2() throws RuntimeException, SystemGlobalException {
        try {

            String json = """
                {
                  "nome": "João",
                  "idade": 30,
                  "CPF": 11330176650,
                  "endereco": {
                    "rua": "Rua A",
                    "bairro": "Centro"
                  },
                  "telefone": ["123456789", "987654321"],
                  "teste": {
                      "teste": {
                          "teste": "teste"
                      }
                   }
                }
                """;

            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(json);

            MDC.put("userId", UUID.randomUUID().toString());
            MDC.put("whitelabelId", UUID.randomUUID().toString());
            MDC.put("requestId", UUID.randomUUID().toString());

            Input input = this.inputService.createInput(new CreateInputData("Example", rootNode));

            RootTreeDynamic createdRoot = this.rootTreeDynamic.create("ROOT Teste", input.getId());

            NodeTree<?> node1 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 1", createdRoot.getId(), null));

            NodeTree<?> node2 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 3", createdRoot.getId(), null));

            NodeTree<?> createdNode2 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 2", node1.getId(), null));

            NodeTree<?> nodeIf1 = this.nodeTreeIfService.createIf(new NodeIfCreationData(node1.getId(), null));

            NodeTree<?> nodeIf2 = this.nodeTreeIfService.createIf(new NodeIfCreationData(nodeIf1.getId(), NodeTreeIf.SetNode.CONDITIONAL));

            NodeTree<?> node4 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 5", nodeIf1.getId(), NodeTreeIf.SetNode.THEN));

            NodeTree<?> node5 = this.nodeTreeBlockService.createBlock(new NodeCreationData("NODE 6", nodeIf1.getId(), NodeTreeIf.SetNode.ELSE));

            NodeTree<?> nodeIf3 = this.nodeTreeIfService.createIf(new NodeIfCreationData(nodeIf1.getId(), null));

            ComparisonSingular comparisonSingular = this.comparisonSingularService.create(new ComparisonSingularRecord(
                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                    "endereco$bairro@",
                    "Centro",
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.CONDITIONAL
            ));

            ComparisonSingular comparisonSingular2 = this.comparisonSingularService.create(new ComparisonSingularRecord(
                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                    ".endereco*bairro/",
                    "Centro",
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.CONDITIONAL
            ));

            ComparisonSingular comparisonSingular3 = this.comparisonSingularService.create(new ComparisonSingularRecord(
                    ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                    "/endereco.bairro$",
                    "Centro",
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.CONDITIONAL
            ));

            ComparisonMulti comparisonMulti = this.comparisonMultiService.create(new ComparisonMultiRecord(
                    ComparisonMulti.ComparisonMultiTypeEnum.CONTAINS,
                    ".CPF/",
                    List.of("11330176650"),
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.CONDITIONAL
            ));

            CompanyList companyList = this.companyListService.createCompanyList(
                    "Dealboard",
                    List.of("11330176650"));

            ComparisonCustomList comparisonCustomList = this.comparisonCustomListService.create(
                    ComparisonCustomList.ComparisonCustomListEnum.CONTAINS,
                    "/CPF",
                    companyList.getId(),
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.ELSE
            );
            JsonNode jsonNode = mapper.readTree(json);

            createdRoot = this.rootTreeDynamic.read(createdRoot.getId());

            return createdRoot.traverse(new NodeInput(jsonNode));

        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong in test", e);
        }
    }

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
}