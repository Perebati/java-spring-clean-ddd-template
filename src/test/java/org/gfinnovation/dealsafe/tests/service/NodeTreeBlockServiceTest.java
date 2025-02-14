package org.gfinnovation.dealsafe.tests.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.exception.models.InfrastructureException;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.tests._shared.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlockServiceTest
 * @since v1.0 (10/02/2025)
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: NodeTreeBlockServiceTest")
public class NodeTreeBlockServiceTest extends GenericTest {
    @Autowired
    private NodeTreeBlockService nodeTreeBlockService;

    @Autowired
    private RootTreeStaticService rootTreeStaticService;

    @Test
    @Transactional
    public void createNodeBlock(){
        String nodeBlockName = "test";

        RootTreeStatic rootTreeStatic = this.rootTreeStaticService.create("teste", PredefinedTypeEnum.TESTE);

        NodeTreeBlock nodeTreeBlock = this.nodeTreeBlockService.createBlock(new NodeCreationData(
                nodeBlockName, rootTreeStatic.getId(), null));

        assertNotNull(nodeTreeBlock);
        assertNotNull(nodeTreeBlock.getId());
        assertEquals(nodeBlockName, nodeTreeBlock.getName());
        assertEquals(nodeTreeBlock.getParentId(), rootTreeStatic.getId());
    }

    @Test
    @Transactional
    public void updateNodeBlock(){
        String nodeBlockName1 = "test 1";
        String nodeBlockName2 = "test 2";

        RootTreeStatic rootTreeStatic = this.rootTreeStaticService.create("teste", PredefinedTypeEnum.TESTE);

        NodeTreeBlock nodeTreeBlock = this.nodeTreeBlockService.createBlock(new NodeCreationData(
                nodeBlockName1, rootTreeStatic.getId(), null));

        assertNotNull(nodeTreeBlock);
        assertNotNull(nodeTreeBlock.getId());
        assertEquals(nodeBlockName1, nodeTreeBlock.getName());
        assertEquals(nodeTreeBlock.getParentId(), rootTreeStatic.getId());

        nodeTreeBlock = this.nodeTreeBlockService.updateBlock(nodeTreeBlock.getId(), new NodeTreeBlockData(nodeBlockName2));

        assertNotNull(nodeTreeBlock);
        assertEquals(nodeBlockName2, nodeTreeBlock.getName());
    }

    @Test
    @Transactional
    public void deleteNodeBlock(){
        String nodeBlockName = "test";

        RootTreeStatic rootTreeStatic = this.rootTreeStaticService.create("teste", PredefinedTypeEnum.TESTE);

        NodeTreeBlock nodeTreeBlock = this.nodeTreeBlockService.createBlock(new NodeCreationData(
                nodeBlockName, rootTreeStatic.getId(), null));

        assertNotNull(nodeTreeBlock);
        assertNotNull(nodeTreeBlock.getId());
        assertEquals(nodeBlockName, nodeTreeBlock.getName());
        assertEquals(nodeTreeBlock.getParentId(), rootTreeStatic.getId());

        this.nodeTreeBlockService.deleteBlock(nodeTreeBlock.getId());

        assertThrows(InfrastructureException.class, () -> this.nodeTreeBlockService.read(nodeTreeBlock.getId()));
    }
}
