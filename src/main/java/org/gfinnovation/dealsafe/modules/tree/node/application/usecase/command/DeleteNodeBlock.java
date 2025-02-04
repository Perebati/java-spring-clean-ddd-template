package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteNodeBlock extends NullOutputUseCase<UUID> {
    private final NodeTreeBlockService blockService;

    public DeleteNodeBlock(NodeTreeBlockService blockService) {
        this.blockService = blockService;
    }

    @Override
    public void execute(UUID input) {
        this.blockService.deleteSync(input);
    }
}
