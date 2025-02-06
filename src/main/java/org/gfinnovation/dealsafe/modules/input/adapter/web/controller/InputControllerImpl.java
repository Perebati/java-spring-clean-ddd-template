package org.gfinnovation.dealsafe.modules.input.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.controller.interfaces.InputController;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputController
 * @since 30/10/2024
 */

@Controller
class InputControllerImpl implements InputController {
    private final InputService inputService;

    public InputControllerImpl(InputService inputService) {
        this.inputService = inputService;
    }

    @Override
    public ResponseEntity<Input> createInput(@NonNull @RequestParam String name,
                                             @NonNull @RequestBody String json) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.inputService.create(name, json));

        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in input creation.");
        }
    }

    @Override
    public ResponseEntity<Input> readInput(@NonNull UUID id) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.inputService.read(id));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in input reading.");
        }
    }
}