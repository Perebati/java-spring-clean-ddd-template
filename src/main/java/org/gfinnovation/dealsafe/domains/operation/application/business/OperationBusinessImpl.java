package org.gfinnovation.dealsafe.domains.operation.application.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ActionOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ComparisonOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.application.business.interfaces.OperationBusiness;
import org.springframework.stereotype.Service;

/**
 * Operations will need an overhaul. Operation will need to be indexed to logic gates.
 * A logic gate will reference a comparison operation and an action if the comparison is positive.
 * Logic gate can be: "DO", "IF". Both of then can reference themselves. "IF" gate will be able to reference an "ELSE" gate.
 * An "ELSE" gate can have an "DO" and/or "IF" references.
 * An "IF" gate will need a type to specify if the operation is "AND", "NAND", "OR", "NOR", "NOT".
 * One thing, I believe that would be better to create groups of "conditions", each onde with a gate reference.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationBusinessImpl
 * @since 30/10/2024
 */
@Service
@RequiredArgsConstructor
@Getter
class OperationBusinessImpl implements OperationBusiness {
    private final ActionOperationBusiness actionOperationBusiness;
    private final ComparisonOperationBusiness comparisonOperationBusiness;
}
