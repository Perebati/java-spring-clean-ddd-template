package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.types;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.ComparisonSingularOperation;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class IsMoreThanOperation
 * @since v1.0 (30/11/2024)
 */
public class IsMoreThanOperation extends ComparisonSingularOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        Number valueA = parseNumericValue(a);
        Number valueB = parseNumericValue(b);

        if (valueA == null || valueB == null) {
            throw new IllegalArgumentException("Não foi possível converter um ou ambos os valores para tipos numéricos.");
        }

        return Double.compare(valueA.doubleValue(), valueB.doubleValue()) > 0;
    }
}
