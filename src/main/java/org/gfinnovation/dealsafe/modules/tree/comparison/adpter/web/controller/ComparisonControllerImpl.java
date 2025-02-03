package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller.interfaces.ComparisonController;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
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
class ComparisonControllerImpl implements ComparisonController {
    private final ComparisonSingularService operationService;

    public ComparisonControllerImpl(ComparisonSingularService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResponseEntity<ComparisonSingular> createSingularComparison(
            ComparisonSingularRecord request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.operationService.create(
                            request.type(),
                            request.jsonPath(),
                            request.variable(),
                            request.nodeId()
                    ));
        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonMulti> createMultiComparison(ComparisonMultiRecord request) throws DomainException, BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<ComparisonSingular> createBlackListComparison(ComparisonCustomListRecord request) throws DomainException, BadRequestException {
        return null;
    }

    @Override
    public ResponseEntity<ComparisonSingular> createWhiteListComparison(ComparisonCustomListRecord request) throws DomainException, BadRequestException {
        return null;
    }
}