package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.UseCase;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateComparisonBlackList implements UseCase<ComparisonCustomListRecord, ComparisonMulti> {
    private final ComparisonMultiService comparisonMultiService;
    private final CompanyListService companyListService;

    @Autowired
    public CreateComparisonBlackList(
            ComparisonMultiService comparisonMultiService,
            CompanyListService companyListService
    ) {
        this.comparisonMultiService = comparisonMultiService;
        this.companyListService = companyListService;
    }

    @Override
    @Transactional
    public ComparisonMulti execute(ComparisonCustomListRecord input) {
        try{
            CompanyList companyList = this.companyListService.read(input.comparisonListId());
            return this.comparisonMultiService.create(
                new ComparisonMultiRecord(
                        ComparisonMulti.ComparisonMultiTypeEnum.NOT_CONTAINS,
                        input.jsonPath(),
                        companyList.getCnpjs(),
                        input.parentId(),
                        input.position()
                ));
        } catch (Exception e) {
            throw new DomainException("Ocorreu um erro inesperado ao criar uma comparação usando BlackList");
        }
    }
}
