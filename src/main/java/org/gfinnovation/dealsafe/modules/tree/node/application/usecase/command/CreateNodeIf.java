package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateNodeIf
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateNodeIf extends UseCase<NodeCreationDTO, NodeTreeIf> {
    private final NodeTreeIfService nodeTreeIfService;

    public CreateNodeIf(NodeTreeIfService nodeTreeIfService) {
        this.nodeTreeIfService = nodeTreeIfService;
    }

    @Override
    public NodeTreeIf execute(NodeCreationDTO input) {
        return this.nodeTreeIfService.create(input);
    }
}
