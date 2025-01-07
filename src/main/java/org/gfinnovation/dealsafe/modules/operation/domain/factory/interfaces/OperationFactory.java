package org.gfinnovation.dealsafe.modules.operation.domain.factory.interfaces;

import org.gfinnovation.dealsafe.modules.operation.domain.factory.components.interfaces.ActionOperationFactory;
import org.gfinnovation.dealsafe.modules.operation.domain.factory.components.interfaces.ComparisonOperationFactory;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationFactory
 * @since 30/10/2024
 */

public interface OperationFactory {
    ComparisonOperationFactory getComparisonOperationFactory();

    ActionOperationFactory getActionOperationFactory();
}
