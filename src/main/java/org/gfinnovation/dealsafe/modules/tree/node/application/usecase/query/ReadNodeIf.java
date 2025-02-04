package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReadNodeIf extends UseCase<UUID, NodeTreeIf> {
    private final NodeTreeIfService nodeTreeIfService;

    public ReadNodeIf(NodeTreeIfService nodeTreeIfService) {
        this.nodeTreeIfService = nodeTreeIfService;
    }

    @Override
    public NodeTreeIf execute(UUID input) {
        return this.nodeTreeIfService.read(input);
    }
}