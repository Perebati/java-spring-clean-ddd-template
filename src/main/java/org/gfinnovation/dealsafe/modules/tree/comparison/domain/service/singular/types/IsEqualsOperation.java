package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.types;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.ComparisonSingularOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class IsEqualsOperation
 * @since 30/10/2024
 */
public class IsEqualsOperation extends ComparisonSingularOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        return a.equals(b);
    }
}
