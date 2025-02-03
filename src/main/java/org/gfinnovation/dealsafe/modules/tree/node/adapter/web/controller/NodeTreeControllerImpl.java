package org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.controller.interfaces.NodeTreeContoller;
import org.gfinnovation.dealsafe.modules.tree.node.adapter.web.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
class NodeTreeControllerImpl implements NodeTreeContoller {
    private final NodeTreeBlockService nodeTreeBlockService;

    NodeTreeControllerImpl(
            NodeTreeBlockService nodeTreeBlockService) {
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    public ResponseEntity<Void> createNode(
            NodeCreationDTO request
    ) throws DomainException {
        try {
            this.nodeTreeBlockService.create(
                    request
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in node creation.");
        }
    }
}

