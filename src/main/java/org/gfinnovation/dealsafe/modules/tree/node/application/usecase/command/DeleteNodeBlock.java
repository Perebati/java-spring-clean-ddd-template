package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class DeleteNodeBlock
 * @since v1.0 (06/02/2025)
 */
@Component
public class DeleteNodeBlock extends NullOutputUseCase<UUID, NodeTreeBlockService> {
    protected DeleteNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        super(nodeTreeBlockService);
    }

    @Override
    public void execute(UUID input) {
        this.service.delete(input);
    }
}
