package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadComparisonSingular
 * @since v1.0 (06/02/2025)
 */
@Component
public class ReadComparisonSingular extends UseCase<UUID, Optional<ComparisonSingular>, ComparisonSingularService> {
    public ReadComparisonSingular(ComparisonSingularService comparisonSingularService) {
        super(comparisonSingularService);
    }

    @Override
    public Optional<ComparisonSingular> execute(UUID input) {
        return this.service.findById(input);
    }
}