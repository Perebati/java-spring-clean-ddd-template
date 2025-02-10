package org.gfinnovation.dealsafe.modules.tree.comparison.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ReadComparisonMulti
 * @since v1.0 (06/02/2025)
 */
@Component
public class ReadComparisonMulti extends UseCase<UUID, ComparisonMulti, ComparisonMultiService> {
    public ReadComparisonMulti(ComparisonMultiService comparisonMultiService) {
        super(comparisonMultiService);
    }

    @Override
    public ComparisonMulti execute(UUID input) {
        return service.read(input);
    }
}