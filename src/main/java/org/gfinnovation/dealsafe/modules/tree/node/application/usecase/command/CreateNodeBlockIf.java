package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationIfDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

@Component
public class CreateNodeBlockIf implements UseCase<NodeCreationIfDTO, NodeTreeBlock> {
    private final NodeTreeBlockService nodeTreeBlockService;

    public CreateNodeBlockIf(NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public NodeTreeBlock execute(NodeCreationIfDTO input) {
        return this.nodeTreeBlockService.create(input);
    }
}
