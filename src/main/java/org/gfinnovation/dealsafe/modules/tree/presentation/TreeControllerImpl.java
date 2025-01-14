package org.gfinnovation.dealsafe.modules.tree.presentation;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.application.service.interfaces.TreeService;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.presentation.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.presentation.dto.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.modules.tree.presentation.dto.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.modules.tree.presentation.interfaces.TreeController;
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
    private final TreeService treeService;

    public TreeControllerImpl(TreeService treeService) {
        this.treeService = treeService;
    }

    @Override
    public ResponseEntity<CompletableFuture<RootTreeStatic>> createRootPredefined(@RequestBody RootCreationPredefinedInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.treeService.getRootTreeBusiness().create(
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
                    this.treeService.getRootTreeBusiness().create(
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
    public ResponseEntity<NodeTree> createNode(NodeCreationDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.treeService.getNodeTreeBusiness().create(
                            request.name(),
                            request.sequence(),
                            request.parent_id()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in node creation.");
        }
    }
}

