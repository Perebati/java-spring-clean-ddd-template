package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.types;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi.ComparisonMultiOperation;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ContainsOperation
 * @since v1.0 (06/02/2025)
 */
public class ContainsOperation extends ComparisonMultiOperation {
    @Override
    public Boolean doOperation(String a, List<String> b) {
        return b.contains(a);
    }
}
