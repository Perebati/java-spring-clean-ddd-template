package org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.domains.operation.application.business.interfaces.OperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.dto.request.OperationCreationDTO;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.inbound.interfaces.OperationController;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationController
 * @since 30/10/2024
 */
@Controller
public class OperationControllerImpl implements OperationController {
    private final OperationBusiness operationBusiness;

    public OperationControllerImpl(OperationBusiness operationBusiness) {
        this.operationBusiness = operationBusiness;
    }

    @Override
    public ResponseEntity<ComparisonOperationEntity> createOperation(OperationCreationDTO request) {
        try {
            return ResponseEntity.ok(
                    this.operationBusiness.getComparisonOperationBusiness().create(
                            UUID.fromString(MDC.get("user_id")),
                            UUID.fromString(MDC.get("company_id")),
                            request.type(),
                            request.jsonPath(),
                            request.variable(),
                            request.node_id()
                    ));
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
