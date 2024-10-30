package org.gfinnovation.dealsafe.domains.operation.application.business.interfaces;

import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ActionOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ComparisonOperationBusiness;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationBusiness
 * @authorNote n/a
 * @since 30/10/2024
 */
public interface OperationBusiness {
    ActionOperationBusiness getActionOperationBusiness();

    ComparisonOperationBusiness getComparisonOperationBusiness();
}
