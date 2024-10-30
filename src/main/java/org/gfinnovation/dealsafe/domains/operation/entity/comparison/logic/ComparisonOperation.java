package org.gfinnovation.dealsafe.domains.operation.entity.comparison.logic;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperation
 * @authorNote This is generic class that performs validations via comparisons.
 * Each class has its own validation method. The operations that they
 * perform is informed on their name.
 * @since 30/10/2024
 */
public class ComparisonOperation {
    public Boolean doOperation(String a, String b) {
        return null;
    }

    public Number parseNumericValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e2) {
                try {
                    return Float.parseFloat(value);
                } catch (NumberFormatException e3) {
                    return null;
                }
            }
        }
    }
}
