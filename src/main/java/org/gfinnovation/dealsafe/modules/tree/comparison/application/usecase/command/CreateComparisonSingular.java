package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.command;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class CreateComparisonSingular
 * @since v1.0 (06/02/2025)
 */
@Component
public class CreateComparisonSingular extends UseCase<ComparisonSingularRecord, ComparisonSingular> {
    private final ComparisonSingularService comparisonSingularService;


    public CreateComparisonSingular(ComparisonSingularService comparisonSingularService) {
        this.comparisonSingularService = comparisonSingularService;
    }

    @Override
    public ComparisonSingular execute(ComparisonSingularRecord input) throws BadRequestException {
        return this.comparisonSingularService.create(input);
    }
}