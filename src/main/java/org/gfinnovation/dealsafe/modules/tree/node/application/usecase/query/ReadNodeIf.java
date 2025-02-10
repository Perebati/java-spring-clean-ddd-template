package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeIfService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadNodeIf
 * @since v1.0 (06/02/2025)
 */
@Component
public class ReadNodeIf extends UseCase<UUID, NodeTreeIf, NodeTreeIfService> {
    public ReadNodeIf(NodeTreeIfService nodeTreeIfService) {
        super(nodeTreeIfService);
    }

    @Override
    public NodeTreeIf execute(UUID input) {
        return this.service.read(input);
    }
}