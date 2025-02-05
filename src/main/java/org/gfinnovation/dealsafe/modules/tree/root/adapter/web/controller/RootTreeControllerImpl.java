package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.controller.interfaces.RootTreeController;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeController
 * @since 30/10/2024
 */

@Controller
class RootTreeControllerImpl implements RootTreeController {
    private final RootTreeStaticService rootTreeStaticService;
    private final RootTreeDynamicService rootTreeDynamicService;

    RootTreeControllerImpl(
            RootTreeStaticService rootTreeStaticService,
            RootTreeDynamicService rootTreeDynamicService) {
        this.rootTreeStaticService = rootTreeStaticService;
        this.rootTreeDynamicService = rootTreeDynamicService;
    }

    @Override
    public ResponseEntity<RootTreeStatic> createRootPredefined(
            @RequestBody RootCreationPredefinedInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeStaticService.create(
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
    public ResponseEntity<RootTreeDynamic> createRootDynamic(
            RootCreationDynamicInputDTO request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeDynamicService.create(
                            request.name(),
                            request.dynamicInput_id()
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in dynamic root creation.");
        }
    }
}

