package org.gfinnovation.dealsafe.modules.tree.node.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadNodeBlock
 * @since v1.0 (06/02/2025)
 */
@Component
public class FindNodeBlock extends UseCase<UUID, Optional<NodeTreeBlock>, NodeTreeBlockService> {
    public FindNodeBlock(NodeTreeBlockService nodeTreeBlockService) {
        super(nodeTreeBlockService);
    }

    @Override
    public Optional<NodeTreeBlock> execute(UUID input) throws SystemGlobalException {
        return this.service.findById(input);
    }
}