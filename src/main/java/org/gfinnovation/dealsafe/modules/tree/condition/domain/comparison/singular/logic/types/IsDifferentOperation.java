package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.types;

import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.logic.ComparisonSingularOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsDifferentOperation
 * @since 30/10/2024
 */
public class IsDifferentOperation extends ComparisonSingularOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        return !a.equals(b);
    }
}
