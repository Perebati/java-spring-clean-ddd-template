package org.gfinnovation.dealsafe.tests.repository;

import org.gfinnovation.dealsafe._shared.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.tests._shared.GenericBusinessRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlock2BlockRepositoryTest
 * @since v1.0 (30/11/2024)
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Repository: NodeBlock")
public class NodeTreeBlockRepositoryTest extends GenericBusinessRepositoryTest<NodeTreeBlock> {
    @Autowired
    private NodeTreeBlockRepository nodeTreeBlockRepository;

    @Autowired
    private RootTreeStaticRepository rootTreeStaticRepository;


    @Override
    protected NodeTreeBlock createEntity() throws SystemGlobalException {
        RootTreeStatic rootTreeStatic = this.rootTreeStaticRepository.create(new RootTreeStatic("RootTestH2", PredefinedTypeEnum.TESTE), repositoryAuth);
        return new NodeTreeBlock("NodeTestH2", rootTreeStatic);
    }

    @Override
    protected GenericBusinessRepository<NodeTreeBlock> createRepository() {
        return this.nodeTreeBlockRepository;
    }
}