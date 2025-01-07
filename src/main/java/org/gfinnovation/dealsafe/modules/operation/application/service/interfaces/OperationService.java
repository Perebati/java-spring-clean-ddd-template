package org.gfinnovation.dealsafe.modules.operation.application.service.interfaces;

import org.gfinnovation.dealsafe.modules.operation.application.service.components.interfaces.ActionOperationService;
import org.gfinnovation.dealsafe.modules.operation.application.service.components.interfaces.ComparisonOperationService;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationBusiness
 * @since 30/10/2024
 */
public interface OperationService {
    ActionOperationService getActionOperationBusiness();

    ComparisonOperationService getComparisonOperationBusiness();
}
