package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase2Inputs;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeTreeBlockData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class UpdateNodeBlock
 * @since v1.0 (06/02/2025)
 */
@Component
public class UpdateNodeBlock extends UseCase2Inputs<UUID, NodeTreeBlockData, NodeTreeBlock, NodeTreeBlockService> {
    public UpdateNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        super(nodeTreeBlockService);
    }

    @Override
    public NodeTreeBlock execute(UUID id, NodeTreeBlockData input) {
        return this.service.updateBlock(id, input);
    }
}
