package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.types;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.singular.ComparisonSingularOperation;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class IsEqualsOperation
 * @since v1.0 (30/11/2024)
 */
public class IsEqualsOperation extends ComparisonSingularOperation {
    @Override
    public Boolean doOperation(String a, String b) {
        return a.equals(b);
    }
}
