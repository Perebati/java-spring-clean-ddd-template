package org.gfinnovation.dealsafe.modules.operation.domain.repository;

import org.gfinnovation.dealsafe.modules.operation.domain.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.modules.operation.domain.repository.components.ComparisonOperationRepository;

/**
 * The main interface of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationRepository
 * @since 30/10/2024
 */
public interface OperationRepository {
    ComparisonOperationRepository getComparisonOperationRepository();

    ActionOperationRepository getActionOperationRepository();
}
