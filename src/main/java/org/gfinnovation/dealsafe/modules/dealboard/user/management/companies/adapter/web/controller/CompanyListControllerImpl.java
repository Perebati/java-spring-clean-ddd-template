package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller;

import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller.interfaces.CompanyListController;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command.CreateCompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command.DeleteCompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command.UpdateCompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query.ReadAllCompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.query.ReadCompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CompanyListControllerImpl implements CompanyListController {
    private final CreateCompanyList createCompanyList;
    private final ReadCompanyList readCompanyList;
    private final ReadAllCompanyList readAllCompanyList;
    private final UpdateCompanyList updateCompanyList;
    private final DeleteCompanyList deleteCompanyList;

    public CompanyListControllerImpl(CreateCompanyList createCompanyList,
                                     ReadCompanyList readCompanyList,
                                     ReadAllCompanyList readAllCompanyList,
                                     UpdateCompanyList updateCompanyList,
                                     DeleteCompanyList deleteCompanyList) {
        this.createCompanyList = createCompanyList;
        this.readCompanyList = readCompanyList;
        this.readAllCompanyList = readAllCompanyList;
        this.updateCompanyList = updateCompanyList;
        this.deleteCompanyList = deleteCompanyList;
    }

    @Override
    public ResponseEntity<CompanyList> createList(@NonNull CompanyListData companyListData) throws DomainException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.createCompanyList.execute(companyListData));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in company list creation.");
        }
    }

    @Override
    public ResponseEntity<CompanyList> readList(@NonNull UUID company_list) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.readCompanyList.execute(company_list));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in company list reading.");
        }
    }

    @Override
    public ResponseEntity<List<CompanyList>> realAllList() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.readAllCompanyList.execute(null));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in company list reading.");
        }
    }

    @Override
    public ResponseEntity<CompanyList> updateList(@NonNull CompanyList companyList) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.updateCompanyList.execute(companyList));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in company list updating.");
        }
    }

    @Override
    public ResponseEntity<Void> deleteList(@NonNull UUID company_list) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.deleteCompanyList.execute(company_list));
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Controller: Unexpected error in company list deleting.");
        }
    }
}
