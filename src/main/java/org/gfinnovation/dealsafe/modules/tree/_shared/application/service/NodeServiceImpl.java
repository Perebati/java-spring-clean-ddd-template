package org.gfinnovation.dealsafe.modules.tree._shared.application.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeServiceImpl
 * @since v1.0 (13/02/2025)
 */
@Service
public class NodeServiceImpl extends GenericServiceImpl<Node<NodeInput>, NodeRepository>
        implements NodeService {
    private final RootTreeService rootTreeService;

    protected NodeServiceImpl(NodeRepository nodeRepository,
                              RootTreeService rootTreeService) {
        super(nodeRepository);
        this.rootTreeService = rootTreeService;
    }

    @Transactional
    public void deleteNode(UUID id, Boolean keepHistory) throws SystemGlobalException {
        try {
            this.repository.deleteNode(id, getRepositoryAuth());
            if(keepHistory) this.rootTreeService.keepHistory(id);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong deleting a node", e);
        }
    }
}