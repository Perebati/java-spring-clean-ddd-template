package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class UpdateNodeBlock
 * @since v1.0 (06/02/2025)
 */
@Component
public class UpdateNodeBlock extends UseCase<NodeTreeBlock, NodeTreeBlock> {
    private final NodeTreeBlockService nodeTreeBlockService;

    public UpdateNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public NodeTreeBlock execute(NodeTreeBlock input) {
        return this.nodeTreeBlockService.update(input);
    }
}
