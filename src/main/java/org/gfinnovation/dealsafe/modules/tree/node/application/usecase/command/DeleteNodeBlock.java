package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteNodeBlock implements UseCase<UUID, Void> {
    private final NodeTreeBlockService blockService;

    public DeleteNodeBlock(NodeTreeBlockService blockService) {
        this.blockService = blockService;
    }

    @Override
    public Void execute(UUID input) {
        this.blockService.deleteSync(input);
        return null;
    }
}
