package org.gfinnovation.dealsafe._sandbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputRecord;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
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

    @PostMapping("tree2")
    public boolean testeTree2() throws RuntimeException, JsonProcessingException {
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

            Input input = this.inputService.create(new CreateInputRecord("Example", rootNode));

            RootTreeDynamic createdRoot = this.rootTreeDynamic.create("ROOT Teste", input.getId());

            NodeTree<?> node1 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 1", createdRoot.getId(), null));

            NodeTree<?> node2 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 3", createdRoot.getId(), null));

            NodeTree<?> createdNode2 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 2", node1.getId(), null));

            NodeTree<?> nodeIf1 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 1", node1.getId(), null));

            NodeTree<?> nodeIf2 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 2", nodeIf1.getId(), NodeTreeIf.SetNode.CONDITIONAL));

            NodeTree<?> node4 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 5", nodeIf1.getId(), NodeTreeIf.SetNode.THEN));

            NodeTree<?> node5 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 6", nodeIf1.getId(), NodeTreeIf.SetNode.ELSE));

            NodeTree<?> nodeIf3 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 3", nodeIf1.getId(), null));

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
                    ComparisonMulti.ComparisonMultiTypeEnum.NOT_CONTAINS,
                    ".CPF/",
                    List.of("11330176650"),
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.CONDITIONAL
            ));

            CompanyList companyList = this.companyListService.createCompanyList(
                    "Dealboard",
                    List.of("11330176650"));

            ComparisonCustomList comparisonCustomList = this.comparisonCustomListService.create(
                    ComparisonCustomList.ComparisonCustomListEnum.NOT_CONTAINS,
                    "/CPF",
                    companyList.getId(),
                    nodeIf3.getParentId(),
                    NodeTreeIf.SetNode.ELSE
            );
            JsonNode jsonNode = mapper.readTree(json);


            createdRoot = this.rootTreeDynamic.read(createdRoot.getId());

            createdRoot.traverse(jsonNode);

            throw new AdapterException("sxfsdf");
        } catch (SystemGlobalException e) {
            throw e;
        }
    }
}