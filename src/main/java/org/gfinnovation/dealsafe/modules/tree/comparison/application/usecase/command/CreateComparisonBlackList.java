package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateComparisonBlackList
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateComparisonBlackList extends UseCase<ComparisonCustomListRecord, ComparisonCustomList> {
    private final ComparisonCustomListService comparisonCustomListService;

    @Autowired
    public CreateComparisonBlackList(
            ComparisonCustomListService comparisonCustomListService
    ) {
        this.comparisonCustomListService = comparisonCustomListService;
    }

    @Override
    @Transactional
    public ComparisonCustomList execute(ComparisonCustomListRecord input) throws BadRequestException {
        return this.comparisonCustomListService.create(
                ComparisonCustomList.ComparisonCustomListEnum.NOT_CONTAINS,
                input.jsonPath(),
                input.comparisonListId(),
                input.parentId(),
                input.position()
        );
    }
}