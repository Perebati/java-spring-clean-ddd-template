package org.gfinnovation.dealsafe.modules.tree.operation.domain.valueobjects;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonContext
 * @since 14/01/2025
 */

@AllArgsConstructor
public class ComparisonContext {
    private Map<String, String> data;

    public String getVariable(String jsonPath) {
        return data.get(jsonPath);
    }
}
