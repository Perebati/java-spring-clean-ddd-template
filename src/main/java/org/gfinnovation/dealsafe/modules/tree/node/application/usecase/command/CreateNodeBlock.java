package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

@Component
public class CreateNodeBlock extends UseCase<NodeCreationDTO, NodeTreeBlock> {
    private final NodeTreeBlockService nodeTreeBlockService;

    public CreateNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public NodeTreeBlock execute(NodeCreationDTO input) {
        return nodeTreeBlockService.create(input);
    }
}
