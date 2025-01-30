package org.gfinnovation.dealsafe.modules.tree.comparison.domain.logic.multi.types;

import org.gfinnovation.dealsafe.modules.tree.comparison.domain.logic.multi.ComparisonMultiOperation;

import java.util.List;

public class ContainsOperation extends ComparisonMultiOperation {
    @Override
    public Boolean doOperation(String a, List<String> b) {
        return b.contains(a);
    }
}
