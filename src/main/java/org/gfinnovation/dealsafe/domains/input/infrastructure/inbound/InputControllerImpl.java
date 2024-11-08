package org.gfinnovation.dealsafe.domains.input.infrastructure.inbound;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.infrastructure.inbound.interfaces.InputController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final InputBusiness inputBusiness;

    public InputControllerImpl(InputBusiness inputBusiness) {
        this.inputBusiness = inputBusiness;
    }

    @Override
    public ResponseEntity<InputEntity> createInput(@RequestParam String name, @RequestBody String json) throws DomainException, BadRequestException {
        try {
            if (name == null || name.isEmpty() || json == null || json.isEmpty()) {
                throw new BadRequestException("'name' and/or 'json' fields can't be null!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(this.inputBusiness.create(name, json));

        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in input creation.");
        }
    }

    @Override
    public ResponseEntity<InputEntity> readInput(UUID id) throws DomainException, BadRequestException {
        try {
            if (id == null) {
                throw new BadRequestException("input id can't be null!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(this.inputBusiness.read(id));
        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in input reading.");
        }
    }
}
