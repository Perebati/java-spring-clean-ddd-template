package org.gfinnovation.dealsafe.modules.tree._shared.application.usecase;

import org.gfinnovation.dealsafe._shared.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class DeleteNodeUseCase
 * @since v1.0 (17/02/2025)
 */

@Component
public class DeleteNodeUseCase extends NullOutputUseCase<UUID, NodeTreeService<?>> {

    public DeleteNodeUseCase(NodeTreeService<?> service) {
        super(service);
    }

    @Override
    public void execute(UUID id) {
        service.deleteNode(id);
    }
}
