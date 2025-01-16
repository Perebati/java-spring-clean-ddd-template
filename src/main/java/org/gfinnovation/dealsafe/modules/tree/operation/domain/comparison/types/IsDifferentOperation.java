package org.gfinnovation.dealsafe.modules.tree.operation.domain.comparison.types;

import org.gfinnovation.dealsafe.modules.tree.operation.domain.comparison.logic.ComparisonOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsDifferentOperation
 * @since 30/10/2024
 */
public class IsDifferentOperation extends ComparisonOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        return !a.equals(b);
    }
}
