package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeBlockService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.springframework.stereotype.Component;

@Component
public class CreateComparisonBlackList implements UseCase<ComparisonCustomListRecord, ComparisonMulti> {
    private final ComparisonMultiService comparisonMultiService;
    private final CompanyListService companyListService;
    private final NodeTreeBlockService nodeTreeBlockService;

    public CreateComparisonBlackList(
            ComparisonMultiService comparisonMultiService,
            CompanyListService companyListService,
            NodeTreeBlockService nodeTreeBlockService
    ) {
        this.comparisonMultiService = comparisonMultiService;
        this.companyListService = companyListService;
        this.nodeTreeBlockService = nodeTreeBlockService;
    }

    @Override
    @Transactional
    public ComparisonMulti execute(ComparisonCustomListRecord input) {
        try{
            NodeTreeBlock nodeTreeBlock = this.nodeTreeBlockService.read(input.nodeId());
            CompanyList companyList = this.companyListService.read(input.comparisonListId());

            ComparisonMulti newComp = this.comparisonMultiService.create(
                    ComparisonMulti.ComparisonMultiTypeEnum.NOT_CONTAINS,
                    input.jsonPath(),
                    companyList.getCnpjs(),
                    input.nodeId());

            nodeTreeBlock.addNode(newComp);
            this.nodeTreeBlockService.updateSync(nodeTreeBlock);
            return newComp;
        } catch (Exception e) {
            throw new DomainException("Ocorreu um erro inesperado ao criar uma comparação usando BlackList");
        }
    }
}
