package org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.domains.operation.application.business.interfaces.OperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.dto.request.OperationCreationDTO;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.interfaces.OperationController;
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
    private final OperationBusiness operationBusiness;

    public OperationControllerImpl(OperationBusiness operationBusiness) {
        this.operationBusiness = operationBusiness;
    }

    @Override
    public ResponseEntity<ComparisonOperationEntity> createOperation(OperationCreationDTO request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.operationBusiness.getComparisonOperationBusiness().create(
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
