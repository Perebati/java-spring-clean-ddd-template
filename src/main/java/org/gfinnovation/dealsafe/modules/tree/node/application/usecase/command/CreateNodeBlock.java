package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationData;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateNodeBlock
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateNodeBlock extends UseCase<NodeCreationData, NodeTreeBlock, NodeTreeBlockService> {
    public CreateNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        super(nodeTreeBlockService);
    }

    @Override
    public NodeTreeBlock execute(NodeCreationData input) {
        return service.createBlock(input);
    }
}
