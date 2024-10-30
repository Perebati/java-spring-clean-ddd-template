package org.gfinnovation.dealsafe.domains.operation.entity.repository;

import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ComparisonOperationRepository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationRepository
 * @authorNote The main interface of this domain.
 * @since 30/10/2024
 */
public interface OperationRepository {
    ComparisonOperationRepository getComparisonOperationRepository();

    ActionOperationRepository getActionOperationRepository();
}
