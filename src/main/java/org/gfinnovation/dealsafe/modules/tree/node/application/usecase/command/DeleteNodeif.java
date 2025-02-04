package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteNodeif implements UseCase<UUID, Void> {
    private final NodeTreeIfService nodeTreeIfService;

    public DeleteNodeif(NodeTreeIfService nodeTreeIfService) {
        this.nodeTreeIfService = nodeTreeIfService;
    }

    @Override
    public Void execute(UUID input) {
        this.nodeTreeIfService.deleteSync(input);
        return null;
    }
}
