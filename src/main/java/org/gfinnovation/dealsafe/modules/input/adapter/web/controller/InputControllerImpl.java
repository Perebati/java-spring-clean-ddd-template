package org.gfinnovation.dealsafe.modules.input.adapter.web.controller;

import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.controller.interfaces.InputController;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.application.usecase.command.CreateInputUseCase;
import org.gfinnovation.dealsafe.modules.input.application.usecase.query.ReadInputUseCase;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InputController
 * @since v1.0 (30/11/2024)
 */
@Controller
@AllArgsConstructor
class InputControllerImpl implements InputController {
    private final CreateInputUseCase createInputUseCase;
    private final ReadInputUseCase readInputUseCase;


    @Override
    public ResponseEntity<Input> createInput(@NonNull CreateInputData request)
            throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.createInputUseCase.execute(request));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in input creation.");
        }
    }

    @Override
    public ResponseEntity<Input> readInput(@NonNull UUID id)
            throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.readInputUseCase.execute(id));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in input reading.");
        }
    }
}