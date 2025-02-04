package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

@Component
public class UpdateNodeBlock implements UseCase<NodeTreeBlock, NodeTreeBlock> {
    private final NodeTreeBlockService nodeTreeBlockService;

    public UpdateNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public NodeTreeBlock execute(NodeTreeBlock input) {
        return this.nodeTreeBlockService.updateSync(input);
    }
}
