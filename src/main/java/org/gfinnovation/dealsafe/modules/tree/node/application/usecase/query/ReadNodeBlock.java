package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReadNodeBlock extends UseCase<UUID, NodeTreeBlock> {
    private final NodeTreeBlockService nodeTreeBlockService;

    public ReadNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public NodeTreeBlock execute(UUID input) {
        return this.nodeTreeBlockService.read(input);
    }
}