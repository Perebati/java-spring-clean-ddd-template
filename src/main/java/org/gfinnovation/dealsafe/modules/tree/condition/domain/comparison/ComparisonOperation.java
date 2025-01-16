package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.ComparisonTypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * An comparison compares variables (:
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperation
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonOperation extends GenericBusinessEntity {
    private ComparisonTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private Set<String> expectedVars = new HashSet<>();
    private Set<ActionOperation> actions = new HashSet<>();

    @Default
    public ComparisonOperation(
            ComparisonTypeEnum comparisonTypeEnum,
            String jsonPath,
            Set<String> variables,
            Set<ActionOperation> actions
    ) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
        this.actions = actions;
    }

    public ComparisonOperation(
            ComparisonTypeEnum comparisonTypeEnum,
            String jsonPath,
            Set<String> variables
    ) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
    }

    public ComparisonOperation(Set<ActionOperation> actions) {
        this.actions = actions;
    }

    public void addVariable(String variable) {
        this.expectedVars.add(variable);
    }

    public void addAction(ActionOperation action) {
        this.actions.add(action);
    }
}
