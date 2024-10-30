package org.gfinnovation.dealsafe.domains.operation.entity.comparison.types;

import org.gfinnovation.dealsafe.domains.operation.entity.comparison.logic.ComparisonOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsMoreThanOrEqualOperation
 * @authorNote n/a
 * @since 30/10/2024
 */
public class IsMoreThanOrEqualOperation extends ComparisonOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        Number valueA = parseNumericValue(a);
        Number valueB = parseNumericValue(b);

        if (valueA == null || valueB == null) {
            throw new IllegalArgumentException("Não foi possível converter um ou ambos os valores para tipos numéricos.");
        }

        return Double.compare(valueA.doubleValue(), valueB.doubleValue()) >= 0;
    }


}
