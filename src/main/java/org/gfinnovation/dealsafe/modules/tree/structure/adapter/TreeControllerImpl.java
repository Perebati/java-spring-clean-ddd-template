package org.gfinnovation.dealsafe.modules.tree.structure.adapter;

import org.gfinnovation.dealsafe.modules.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.interfaces.TreeController;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces.RootTreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
class TreeControllerImpl implements TreeController {
    private final RootTreeService rootTreeService;
    private final NodeTreeService nodeTreeService;

    TreeControllerImpl(RootTreeService rootTreeService, NodeTreeService nodeTreeService) {
        this.rootTreeService = rootTreeService;
        this.nodeTreeService = nodeTreeService;
    }

    @Override
    public ResponseEntity<RootTreeStatic> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeService.create(
                            request.name(),
                            request.type()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in static root creation.");
        }
    }

    @Override
    public ResponseEntity<CompletableFuture<RootTreeDynamic>> createRootPredefined(RootCreationDynamicInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeService.create(
                            request.name(),
                            request.dynamicInput_id()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in dynamic root creation.");
        }
    }

    @Override
    public ResponseEntity<Void> createNode(NodeCreationDTO request) throws DomainException {
        try {
            this.nodeTreeService.create(
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

