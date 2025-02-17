package org.gfinnovation.dealsafe.tests.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
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
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.tests._shared.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class DeleteNodeBlockUseCaseTest
 * @since v1.0 (14/02/2025)
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("UseCase: DeleteNodeBlockUseCaseTest")
public class DeleteNodeBlockUseCaseTest extends GenericTest {
    @Autowired
    private NodeTreeService<?> nodeTreeService;

    @Autowired
    private NodeTreeBlockService nodeTreeBlockService;

    @Autowired
    private NodeTreeIfService nodeTreeIfService;

    @Autowired
    private ComparisonSingularService comparisonSingularService;

    @Autowired
    private ComparisonMultiService comparisonMultiService;

    @Autowired
    private ComparisonCustomListService comparisonCustomListService;

    @Autowired
    private CompanyListService companyListService;

    @Autowired
    private InputService inputService;

    @Autowired
    private RootTreeDynamicService rootTreeDynamic;
    @Test
    @DisplayName("Should create a tree and then validate a json.")
    @Transactional
    public void testeTree() throws JsonProcessingException {

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

        Input input = inputService.createInput(new CreateInputData("Example", rootNode));
        assertNotNull(input, "Input should not be null");

        RootTreeDynamic createdRoot = rootTreeDynamic.create("ROOT Teste", input.getId());
        assertNotNull(createdRoot, "root should not be null");
        assertNotNull(createdRoot.getId(), "ID root should not be null");

        NodeTree<?> node1 = nodeTreeBlockService.createBlock(new NodeCreationData("NODE 1", createdRoot.getId(), null));
        assertNotNull(node1, "NODE 1 should not be null");

        NodeTree<?> node2 = nodeTreeBlockService.createBlock(new NodeCreationData("NODE 3", createdRoot.getId(), null));
        assertNotNull(node2, "NODE 3 should not be null");

        NodeTree<?> createdNode2 = nodeTreeBlockService.createBlock(new NodeCreationData("NODE 2", node1.getId(), null));
        assertNotNull(createdNode2, "NODE 2 should not be null");

        NodeTree<?> nodeIf1 = nodeTreeIfService.createIf(new NodeIfCreationData(node1.getId(), null));
        assertNotNull(nodeIf1, "NODE IF 1 should not be null");

        NodeTree<?> nodeIf2 = nodeTreeIfService.createIf(new NodeIfCreationData(nodeIf1.getId(), NodeTreeIf.SetNode.CONDITIONAL));
        assertNotNull(nodeIf2, "NODE IF 2 should not be null");

        NodeTree<?> node4 = nodeTreeBlockService.createBlock(new NodeCreationData("NODE 5", nodeIf1.getId(), NodeTreeIf.SetNode.THEN));
        assertNotNull(node4, "NODE 5 should not be null");

        NodeTree<?> node5 = nodeTreeBlockService.createBlock(new NodeCreationData("NODE 6", nodeIf1.getId(), NodeTreeIf.SetNode.ELSE));
        assertNotNull(node5, "NODE 6 should not be null");

        NodeTree<?> nodeIf3 = nodeTreeIfService.createIf(new NodeIfCreationData(nodeIf1.getId(), null));
        assertNotNull(nodeIf3, "NODE IF 3 should not be null");

        ComparisonSingular comparisonSingular = comparisonSingularService.create(new ComparisonSingularRecord(
                ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                "endereco$bairro@",
                "Centro",
                nodeIf3.getParentId(),
                NodeTreeIf.SetNode.CONDITIONAL
        ));
        assertNotNull(comparisonSingular, "ComparisonSingular should not be null");

        ComparisonSingular comparisonSingular2 = comparisonSingularService.create(new ComparisonSingularRecord(
                ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                ".endereco*bairro/",
                "Centro",
                nodeIf3.getParentId(),
                NodeTreeIf.SetNode.CONDITIONAL
        ));
        assertNotNull(comparisonSingular2, "ComparisonSingular2 should not be null");

        ComparisonSingular comparisonSingular3 = comparisonSingularService.create(new ComparisonSingularRecord(
                ComparisonSingular.ComparisonSingularTypeEnum.EQUAL,
                "/endereco.bairro$",
                "Centro",
                nodeIf3.getParentId(),
                NodeTreeIf.SetNode.CONDITIONAL
        ));
        assertNotNull(comparisonSingular3, "ComparisonSingular3 should not be null");

        ComparisonMulti comparisonMulti = comparisonMultiService.create(new ComparisonMultiRecord(
                ComparisonMulti.ComparisonMultiTypeEnum.NOT_CONTAINS,
                ".CPF/",
                List.of("11330176650"),
                nodeIf3.getParentId(),
                NodeTreeIf.SetNode.CONDITIONAL
        ));
        assertNotNull(comparisonMulti, "ComparisonMulti should not be null");

        CompanyList companyList = companyListService.createCompanyList("Dealboard", List.of("11330176650"));
        assertNotNull(companyList, "CompanyList should not be null");

        ComparisonCustomList comparisonCustomList = comparisonCustomListService.create(
                ComparisonCustomList.ComparisonCustomListEnum.NOT_CONTAINS,
                "/CPF",
                companyList.getId(),
                nodeIf3.getParentId(),
                NodeTreeIf.SetNode.ELSE
        );
        assertNotNull(comparisonCustomList, "ComparisonCustomList should not be null");

        this.nodeTreeService.deleteNode(node1.getId());

        RootTree<?> rootTree = this.rootTreeDynamic.read(createdRoot.getId());

        assertThrows(RuntimeException.class, () -> nodeTreeBlockService.findById(node1.getId()));
        assertThrows(RuntimeException.class, () -> nodeTreeIfService.read(nodeIf1.getId()));


        System.out.println("Teste");
    }
}
