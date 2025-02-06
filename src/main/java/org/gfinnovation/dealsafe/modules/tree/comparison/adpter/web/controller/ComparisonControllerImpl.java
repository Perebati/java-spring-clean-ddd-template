package org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.controller.interfaces.ComparisonController;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonBlackList;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command.CreateComparisonWhiteList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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

    @Autowired
    ComparisonControllerImpl(CreateComparisonBlackList createComparisonBlackList,
                             CreateComparisonSingular createComparisonSingular,
                             CreateComparisonMulti createComparisonMulti,
                             CreateComparisonWhiteList createComparisonWhiteList
    ) {
        this.createComparisonBlackList = createComparisonBlackList;
        this.createComparisonSingular = createComparisonSingular;
        this.createComparisonMulti = createComparisonMulti;
        this.createComparisonWhiteList = createComparisonWhiteList;
    }


    @Override
    public ResponseEntity<ComparisonSingular> createSingularComparison(
            @NonNull ComparisonSingularRecord request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonSingular.execute(
                            request
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonMulti> createMultiComparison(
            @NonNull ComparisonMultiRecord request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonMulti.execute(
                            request
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonCustomList> createBlackListComparison(
            @NonNull ComparisonCustomListRecord request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonBlackList.execute(
                            request
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in operation creation.");
        }
    }

    @Override
    public ResponseEntity<ComparisonCustomList> createWhiteListComparison(
            @NonNull ComparisonCustomListRecord request) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.createComparisonWhiteList.execute(
                            request
                    ));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in operation creation.");
        }
    }
}