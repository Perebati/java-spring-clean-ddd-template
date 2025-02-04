package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller.interfaces.ComparisonController;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonBlackList;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonWhiteList;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query.ReadComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query.ReadComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CreateComparisonBlackList createComparisonBlackList;
    private final CreateComparisonSingular createComparisonSingular;
    private final CreateComparisonMulti createComparisonMulti;
    private final CreateComparisonWhiteList createComparisonWhiteList;
    private final ReadComparisonSingular readComparisonSingular;
    private final ReadComparisonMulti readComparisonMulti;

    @Autowired
    ComparisonControllerImpl(CreateComparisonBlackList createComparisonBlackList,
                             CreateComparisonSingular createComparisonSingular,
                             CreateComparisonMulti createComparisonMulti,
                             CreateComparisonWhiteList createComparisonWhiteList,
                             ReadComparisonSingular readComparisonSingular,
                             ReadComparisonMulti readComparisonMulti) {
        this.createComparisonBlackList = createComparisonBlackList;
        this.createComparisonSingular = createComparisonSingular;
        this.createComparisonMulti = createComparisonMulti;
        this.createComparisonWhiteList = createComparisonWhiteList;
        this.readComparisonSingular = readComparisonSingular;
        this.readComparisonMulti = readComparisonMulti;
    }


    @Override
    public ResponseEntity<ComparisonSingular> createSingularComparison(
            ComparisonSingularRecord request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonSingular.execute(
                            request
                    ));
        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonMulti> createMultiComparison(
            ComparisonMultiRecord request) throws DomainException, BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonMulti.execute(
                            request
                    ));
        } catch (DomainException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonCustomList> createBlackListComparison(
            ComparisonCustomListRecord request) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonBlackList.execute(
                            request
                    ));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonCustomList> createWhiteListComparison(
            ComparisonCustomListRecord request) throws DomainException {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        this.createComparisonWhiteList.execute(
                                request
                        ));
            } catch (DomainException e) {
                throw e;
            } catch (Exception e) {
                throw new DomainException("Controller: Unexpected error in operation creation.");
            }
        }
}