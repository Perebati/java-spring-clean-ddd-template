package org.gfinnovation.dealsafe.modules.tree._shared.application;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.application.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeRepository;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeServiceImpl
 * @since v1.0 (13/02/2025)
 */
@Service
class NodeServiceImpl
        extends GenericServiceImpl<Node<NodeInput>, NodeRepository>
        implements NodeService {
    protected NodeServiceImpl(NodeRepository nodeRepository) {
        super(nodeRepository);
    }
}
