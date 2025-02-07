package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class UpdateNodeIf
 * @since v1.0 (06/02/2025)
 */
@Component
public class UpdateNodeIf extends UseCase<NodeTreeIf, NodeTreeIf, NodeTreeIfService> {
    public UpdateNodeIf(NodeTreeIfService nodeTreeIfService) {
        super(nodeTreeIfService);
    }

    @Override
    public NodeTreeIf execute(NodeTreeIf input) {
        return this.service.update(input);
    }
}
