package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.types;

import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.ComparisonSingularOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsLessThanOrEqualOperation
 * @since 30/10/2024
 */
public class IsLessThanOrEqualOperation extends ComparisonSingularOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        Number valueA = parseNumericValue(a);
        Number valueB = parseNumericValue(b);

        if (valueA == null || valueB == null) {
            throw new IllegalArgumentException("Não foi possível converter um ou ambos os valores para tipos numéricos.");
        }

        return Double.compare(valueA.doubleValue(), valueB.doubleValue()) <= 0;
    }
}
