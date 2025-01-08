package org.gfinnovation.dealsafe.modules.operation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;
import org.gfinnovation.dealsafe._shared.utils.annotations.Default;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * An comparison compares variables (:
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationEntity
 * @since 30/10/2024
 */

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class ComparisonOperationEntity extends GenericBusinessEntity {
    private ComparisonTypeEnum comparisonTypeEnum;
    private String jsonVariablePath;
    private Set<String> expectedVars = new HashSet<>();
    private Set<ActionOperationEntity> actions = new HashSet<>();

    @Default
    public ComparisonOperationEntity(ComparisonTypeEnum comparisonTypeEnum, String jsonPath, Set<String> variables, Set<ActionOperationEntity> actions) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
        this.actions = actions;
    }

    public ComparisonOperationEntity(ComparisonTypeEnum comparisonTypeEnum, String jsonPath, Set<String> variables) {
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
    }

    public ComparisonOperationEntity(Set<ActionOperationEntity> actions) {
        this.actions = actions;
    }

    public void addVariable(String variable) {
        this.expectedVars.add(variable);
    }

    public void addAction(ActionOperationEntity action) {
        this.actions.add(action);
    }
}
