package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller;

import jakarta.annotation.Nonnull;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.AdapterException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.controller.interfaces.CompanyListController;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListCreationData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.usecase.command.CreateCompanyList;
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

    public CompanyListControllerImpl(CreateCompanyList createCompanyList,
                                     ReadCompanyList readCompanyList,
                                     ReadAllCompanyList readAllCompanyList,
                                     UpdateCompanyList updateCompanyList) {
        this.createCompanyList = createCompanyList;
        this.readCompanyList = readCompanyList;
        this.readAllCompanyList = readAllCompanyList;
        this.updateCompanyList = updateCompanyList;
    }

    @Override
    public ResponseEntity<CompanyList> createList(@NonNull CompanyListCreationData companyListData) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.createCompanyList.execute(companyListData));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in company list creation.");
        }
    }

    @Override
    public ResponseEntity<CompanyList> readList(@NonNull UUID company_list) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.readCompanyList.execute(company_list));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in company list reading.");
        }
    }

    @Override
    public ResponseEntity<List<CompanyList>> realAllList() throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.readAllCompanyList.execute(null));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in company list reading.");
        }
    }

    @Override
    public ResponseEntity<CompanyList> updateList(@Nonnull UUID id,
                                                  @NonNull CompanyListData companyList) throws SystemGlobalException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.updateCompanyList.execute(id, companyList));
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException("Unexpected error in company list updating.");
        }
    }
}