package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;

public class CreateComparisonMulti implements UseCase<ComparisonMultiRecord, ComparisonMulti> {
    private final ComparisonMultiService comparisonMultiService;

    public CreateComparisonMulti(ComparisonMultiService comparisonMultiService) {
        this.comparisonMultiService = comparisonMultiService;
    }

    @Override
    public ComparisonMulti execute(ComparisonMultiRecord input) throws BadRequestException {
        return this.comparisonMultiService.create(input.type(),
                input.jsonPath(),
                input.variables(),
                input.nodeId());
    }
}
