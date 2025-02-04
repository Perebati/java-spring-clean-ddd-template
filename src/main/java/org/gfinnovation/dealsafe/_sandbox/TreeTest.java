package org.gfinnovation.dealsafe._sandbox;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeTest
 * @since 30/10/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("teste")
@Tag(name = "Teste")
public class TreeTest {
    private final NodeTreeBlockService nodeTreeBlockService;
    private final RootTreeStaticService rootTreeStaticService;
    private final NodeTreeIfService nodeTreeIfService;

    @PostMapping("tree2")
    public boolean testeTree2() throws RuntimeException {
        try {
            MDC.put("userId", UUID.randomUUID().toString());
            MDC.put("whitelabelId", UUID.randomUUID().toString());
            MDC.put("requestId", UUID.randomUUID().toString());

            RootTreeStatic createdRoot = this.rootTreeStaticService.create("ROOT Teste", PredefinedTypeEnum.TESTE);

            NodeTree<?> node1 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 1", createdRoot.getId(), null));

            NodeTree<?> node2 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 3", createdRoot.getId(), null));

            NodeTree<?> createdNode2 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 2", node1.getId(), null));

            NodeTree<?> nodeIf1 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 1", node1.getId(), null));

            NodeTree<?> nodeIf2 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 2", nodeIf1.getId(), NodeTreeIf.SetNode.CONDITIONAL));

            NodeTree<?> node4 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 5", nodeIf1.getId(), NodeTreeIf.SetNode.THEN));

            NodeTree<?> node5 = this.nodeTreeBlockService.create(new NodeCreationDTO("NODE 6", nodeIf1.getId(), NodeTreeIf.SetNode.ELSE));

            NodeTree<?> nodeIf3 = this.nodeTreeIfService.create(new NodeCreationDTO("NODE IF 3", nodeIf1.getId(), null));


            createdRoot = this.rootTreeStaticService.read(createdRoot.getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}