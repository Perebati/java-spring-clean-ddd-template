package org.gfinnovation.dealsafe.domains.input.infrastructure.inbound;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.infrastructure.inbound.interfaces.InputController;
import org.slf4j.MDC;
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
public class InputControllerImpl implements InputController {
    private final InputBusiness inputBusiness;

    public InputControllerImpl(InputBusiness inputBusiness) {
        this.inputBusiness = inputBusiness;
    }

    @Override
    public ResponseEntity<InputEntity> createInput(@RequestParam String name, @RequestBody String json) throws FactoryException, ValidationException, RepositoryException, BusinessException {
        try {
            return ResponseEntity.ok(
                    this.inputBusiness.create(
                            UUID.fromString(MDC.get("user_id")),
                            UUID.fromString(MDC.get("company_id")),
                            name,
                            json
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
