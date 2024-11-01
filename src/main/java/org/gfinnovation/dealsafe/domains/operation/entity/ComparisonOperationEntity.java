package org.gfinnovation.dealsafe.domains.operation.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.utils.annotations.Default;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    private Set<Object> expectedVars = new HashSet<>();
    private Set<ActionOperationEntity> actions = new HashSet<>();

    @Default
    public ComparisonOperationEntity(UUID user_id, UUID company_id, ComparisonTypeEnum comparisonTypeEnum, String jsonPath, Set<Object> variables, Set<ActionOperationEntity> actions) {
        super(user_id, company_id);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
        this.actions = actions;
    }

    public ComparisonOperationEntity(UUID user_id, UUID company_id, ComparisonTypeEnum comparisonTypeEnum, String jsonPath, Set<Object> variables) {
        super(user_id, company_id);
        this.comparisonTypeEnum = comparisonTypeEnum;
        this.jsonVariablePath = jsonPath;
        this.expectedVars = variables;
    }

    public ComparisonOperationEntity(UUID user_id, UUID company_id, Set<ActionOperationEntity> actions) {
        super(user_id, company_id);
        this.actions = actions;
    }

    public void addVariable(String variable) {
        this.expectedVars.add(variable);
    }

    public void addAction(ActionOperationEntity action) {
        this.actions.add(action);
    }
}
