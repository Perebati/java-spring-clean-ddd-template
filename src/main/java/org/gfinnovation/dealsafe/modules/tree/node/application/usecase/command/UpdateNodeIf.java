package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.stereotype.Component;

@Component
public class UpdateNodeIf implements UseCase<NodeTreeIf, NodeTreeIf> {
    private final NodeTreeIfService nodeTreeIfService;

    public UpdateNodeIf(NodeTreeIfService nodeTreeIfService) {
        this.nodeTreeIfService = nodeTreeIfService;
    }

    @Override
    public NodeTreeIf execute(NodeTreeIf input) {
        return this.nodeTreeIfService.updateSync(input);
    }
}
