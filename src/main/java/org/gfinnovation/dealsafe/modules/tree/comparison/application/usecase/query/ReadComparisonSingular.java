package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadComparisonSingular
 * @since v1.0 (06/02/2025)
 */
@Component
public class ReadComparisonSingular extends UseCase<UUID, ComparisonSingular> {
    private final ComparisonSingularService comparisonSingularService;

    public ReadComparisonSingular(ComparisonSingularService comparisonSingularService) {
        this.comparisonSingularService = comparisonSingularService;
    }

    @Override
    public ComparisonSingular execute(UUID input) throws BadRequestException {
        return comparisonSingularService.read(input);
    }
}