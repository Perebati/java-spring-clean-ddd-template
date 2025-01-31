package org.gfinnovation.dealsafe.modules.tree.comparison.adpter;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.dto.request.ComparisonCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.interfaces.ConditionController;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces.ComparisonSingularDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ConditionController
 * @since 30/10/2024
 */
@Controller
class ConditionControllerImpl implements ConditionController {
    private final ComparisonSingularDomainService operationService;

    public ConditionControllerImpl(ComparisonSingularDomainService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResponseEntity<ComparisonSingular> createOperation(ComparisonCreationDTO request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.operationService.create(
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
