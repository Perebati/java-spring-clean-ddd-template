package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.multi;

import java.util.List;

/**
 * This is generic class that performs validations via comparisons.
 * Each class has its own validation method. The operations that they
 * perform is informed on their name.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class Comparison
 * @since 30/10/2024
 */
public class ComparisonMultiOperation {

    /**
     * Performs a comparison, this is a generic class, so the comparison is
     * done inside the class that inherits this.
     *
     * @param a Comparison parameter.
     * @param b Compared parameter.
     * @return Boolean
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public Boolean doOperation(String a, List<String> b) {
        return null;
    }
}
