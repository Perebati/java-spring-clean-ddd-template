package org.gfinnovation.dealsafe.domains.operation.entity.comparison.logic;

/**
 * This is generic class that performs validations via comparisons.
 * Each class has its own validation method. The operations that they
 * perform is informed on their name.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperation
 * @since 30/10/2024
 */
public class ComparisonOperation {

    /**
     * Performs a comparison, this is a generic class, so the comparison is
     * done inside the class that inherits this.
     *
     * @author Lucas Batista Pereira
     * @param a Comparison parameter.
     * @param b Compared parameter.
     * @return Boolean
     * @since 30/10/2024
     */
    public Boolean doOperation(String a, String b) {
        return null;
    }

    /**
     * Checks if a value is either an Int, Float or Double.
     *
     * @author Lucas Batista Pereira
     * @param value Parsed value.
     * @return Number
     * @since 30/10/2024
     */
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
