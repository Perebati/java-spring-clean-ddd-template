package org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationFactory
 * @since 30/10/2024
 */

public interface ComparisonFactory {
    ComparisonSingular produce(
            ComparisonSingular.ComparisonSingularTypeEnum type,
            String jsonPath, String variable,
            Node<?> parent
    ) throws FactoryException, BadRequestException;

    ComparisonMulti produce(
            ComparisonMulti.ComparisonMultiTypeEnum comparisonTypeEnum,
            String jsonVariablePath,
            List<String> expectedVars,
            Node<?> parent
    ) throws FactoryException, BadRequestException;;
}
