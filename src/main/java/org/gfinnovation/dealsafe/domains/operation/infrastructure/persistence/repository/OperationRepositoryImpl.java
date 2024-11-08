package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.OperationRepository;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ActionOperationRepository;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.components.ComparisonOperationRepository;
import org.springframework.stereotype.Repository;

/**
 * The main repository impl in this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationRepositoryImpl
 * @since 30/10/2024
 */

@Repository
@RequiredArgsConstructor
@Getter
public class OperationRepositoryImpl implements OperationRepository {
    private final ActionOperationRepository actionOperationRepository;
    private final ComparisonOperationRepository comparisonOperationRepository;

}
