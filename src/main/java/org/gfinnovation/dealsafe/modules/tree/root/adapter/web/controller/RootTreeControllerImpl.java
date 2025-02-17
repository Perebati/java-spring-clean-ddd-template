package org.gfinnovation.dealsafe.modules.tree.root.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.controller.interfaces.RootTreeController;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationDynamicInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.adapter.web.request.RootCreationPredefinedInputDTO;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeControllerImpl
 * @since v1.0 (30/11/2024)
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
            @NonNull @RequestBody RootCreationPredefinedInputDTO request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeStaticService.create(
                            request.name(),
                            request.type()
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in static root creation.", e);
        }
    }

    @Override
    public ResponseEntity<RootTreeDynamic> createRootDynamic(
            @NonNull @RequestBody RootCreationDynamicInputDTO request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.rootTreeDynamicService.create(
                            request.name(),
                            request.dynamicInput_id()
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in dynamic root creation.", e);
        }
    }
}

