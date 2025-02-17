package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateComparisonMulti
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateComparisonMulti extends UseCase<ComparisonMultiRecord, ComparisonMulti, ComparisonMultiService> {
    public CreateComparisonMulti(ComparisonMultiService comparisonMultiService) {
        super(comparisonMultiService);
    }

    @Override
    public ComparisonMulti execute(ComparisonMultiRecord input) throws SystemGlobalException {
        return this.service.create(input);
    }
}