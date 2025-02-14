package org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;

import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ComparisonOperationFactory
 * @since v1.0 (30/11/2024)
 */
public interface ComparisonFactory {
    ComparisonSingular produce(
            ComparisonSingular.ComparisonSingularTypeEnum type,
            String jsonPath, String variable,
            Node<?> parent
    ) throws SystemGlobalException;

    ComparisonMulti produce(
            ComparisonMulti.ComparisonMultiTypeEnum comparisonTypeEnum,
            String jsonVariablePath,
            List<String> expectedVars,
            Node<?> parent
    ) throws SystemGlobalException;

    ComparisonCustomList produce(
            ComparisonCustomList.ComparisonCustomListEnum type,
            String jsonPath,
            UUID variable,
            Node<?> parent
    ) throws SystemGlobalException;
}