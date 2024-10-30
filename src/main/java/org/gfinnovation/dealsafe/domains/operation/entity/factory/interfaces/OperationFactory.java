package org.gfinnovation.dealsafe.domains.operation.entity.factory.interfaces;

import org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces.ActionOperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces.ComparisonOperationFactory;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationFactory
 * @authorNote n/a
 * @since 30/10/2024
 */
public interface OperationFactory {
    ComparisonOperationFactory getComparisonOperationFactory();

    ActionOperationFactory getActionOperationFactory();
}
