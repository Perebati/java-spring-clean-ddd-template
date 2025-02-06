package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class DeleteNodeIf
 * @since v1.0 (06/02/2025)
 */
@Component
public class DeleteNodeIf extends NullOutputUseCase<UUID> {
    private final NodeTreeIfService nodeTreeIfService;

    public DeleteNodeIf(NodeTreeIfService nodeTreeIfService) {
        this.nodeTreeIfService = nodeTreeIfService;
    }

    @Override
    public void execute(UUID input) {
        this.nodeTreeIfService.delete(input);
    }
}
