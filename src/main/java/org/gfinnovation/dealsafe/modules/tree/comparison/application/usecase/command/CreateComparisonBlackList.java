package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateComparisonBlackList
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateComparisonBlackList extends UseCase<ComparisonCustomListRecord, ComparisonCustomList, ComparisonCustomListService> {
    public CreateComparisonBlackList(ComparisonCustomListService comparisonCustomListService) {
        super(comparisonCustomListService);
    }

    @Override
    @Transactional
    public ComparisonCustomList execute(ComparisonCustomListRecord input) throws SystemGlobalException {
        return this.service.create(
                ComparisonCustomList.ComparisonCustomListEnum.NOT_CONTAINS,
                input.jsonPath(),
                input.comparisonListId(),
                input.parentId(),
                input.position()
        );
    }
}