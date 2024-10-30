package org.gfinnovation.dealsafe.domains.operation.entity.comparison.types;

import org.gfinnovation.dealsafe.domains.operation.entity.comparison.logic.ComparisonOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsEqualsOperation
 * @authorNote n/a
 * @since 30/10/2024
 */
public class IsEqualsOperation extends ComparisonOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        return a.equals(b);
    }
}
