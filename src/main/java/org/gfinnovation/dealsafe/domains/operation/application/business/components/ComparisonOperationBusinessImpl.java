package org.gfinnovation.dealsafe.domains.operation.application.business.components;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ComparisonOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.interfaces.OperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.OperationRepository;
import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Handles comparisons operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationBusinessImpl
 * @since 30/10/2024
 */

@Component
@RequiredArgsConstructor
public class ComparisonOperationBusinessImpl implements ComparisonOperationBusiness {
    private final TreeBusiness treeBusiness;
    private final OperationRepository operationRepository;
    private final OperationFactory operationFactory;


    /**
     * Creates a comparison operation.
     *
     * @param user_id    UserId.
     * @param company_id CompanyId.
     * @param type       Type of comparison.
     * @param jsonPath   Path to the compared variable.
     * @param variable   Variable to compare.
     * @param node_id    Parent node.
     * @return ComparisonOperationEntity
     * @throws BadRequestException When wrong input from user.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public ComparisonOperationEntity create(UUID user_id, UUID company_id, ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws BadRequestException {
        NodeTreeEntity parent = this.treeBusiness.getNodeTreeBusiness().read(node_id).orElseThrow(() -> new EntityNotFoundException("Parent not found!"));
        ComparisonOperationEntity newOperation = this.operationRepository.getComparisonOperationRepository().create(this.operationFactory.getComparisonOperationFactory().produce(user_id, company_id, type, jsonPath, variable, node_id));
        this.treeBusiness.getNodeTreeBusiness().update(parent.addOperation(newOperation));
        return newOperation;
    }

    @Override
    public Optional<ComparisonOperationEntity> read(UUID id) throws RuntimeException {
        return this.operationRepository.getComparisonOperationRepository().read(id);
    }

    @Override
    public ComparisonOperationEntity update(ComparisonOperationEntity entity) throws RuntimeException {
        return this.operationRepository.getComparisonOperationRepository().update(entity);
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        this.operationRepository.getComparisonOperationRepository().delete(id);
    }

    @Override
    public Optional<List<ComparisonOperationEntity>> readAll() throws RuntimeException {
        return this.operationRepository.getComparisonOperationRepository().findAll();
    }

    @Override
    public Optional<List<ComparisonOperationEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.operationRepository.getComparisonOperationRepository().findAllByIds(ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.operationRepository.getComparisonOperationRepository().check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.operationRepository.getComparisonOperationRepository().checkAll(ids);
    }
}
