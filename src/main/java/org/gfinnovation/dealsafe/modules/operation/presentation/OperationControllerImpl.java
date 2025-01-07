package org.gfinnovation.dealsafe.modules.operation.presentation;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.operation.application.service.interfaces.OperationService;
import org.gfinnovation.dealsafe.modules.operation.domain.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.presentation.dto.request.OperationCreationDTO;
import org.gfinnovation.dealsafe.modules.operation.presentation.interfaces.OperationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationController
 * @since 30/10/2024
 */
@Controller
class OperationControllerImpl implements OperationController {
    private final OperationService operationService;

    public OperationControllerImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResponseEntity<ComparisonOperationEntity> createOperation(OperationCreationDTO request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.operationService.getComparisonOperationBusiness().create(
                            request.type(),
                            request.jsonPath(),
                            request.variable(),
                            request.node_id()
                    ));
        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in operation creation.");
        }
    }
}
