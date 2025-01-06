package org.gfinnovation.dealsafe.domains.operation.application.business.components;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.domains.application.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
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
import java.util.concurrent.CompletableFuture;

/**
 * Handles comparisons operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationBusinessImpl
 * @since 30/10/2024
 */

@Component
public class ComparisonOperationBusinessImpl extends GenericBusinessImpl implements ComparisonOperationBusiness {
    private final TreeBusiness treeBusiness;
    private final OperationRepository operationRepository;
    private final OperationFactory operationFactory;

    public ComparisonOperationBusinessImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            TreeBusiness treeBusiness,
            OperationRepository operationRepository,
            OperationFactory operationFactory
    ) {
        super(userBusiness, companyBusiness);
        this.treeBusiness = treeBusiness;
        this.operationRepository = operationRepository;
        this.operationFactory = operationFactory;
    }

    /**
     * Creates a comparison operation.
     *
     * @param type     Type of comparison.
     * @param jsonPath Path to the compared variable.
     * @param variable Variable to compare.
     * @param node_id  Parent node.
     * @return ComparisonOperationEntity
     * @throws BusinessException   Thrown when an error occurred on business level.
     * @throws FactoryException    Thrown when an error occurred on factory level.
     * @throws ValidationException Thrown when an error occurred on factory level.
     * @throws RepositoryException Thrown when an error occurred on repository level.
     * @throws BadRequestException Thrown when user input is not correct.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public ComparisonOperationEntity create(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws BusinessException, BadRequestException {
        try {
            NodeTreeEntity parent = this.treeBusiness.getNodeTreeBusiness().read(node_id);
            ComparisonOperationEntity newOperation = this.operationFactory.getComparisonOperationFactory().produce(getUserId(), getCompanyId(), type, jsonPath, variable, node_id);
            return this.operationRepository.getComparisonOperationRepository().createComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong creating a comparison operation.", e);
        }
    }

    @Override
    public ComparisonOperationEntity read(UUID id) throws BusinessException {
        try {
            return this.operationRepository.getComparisonOperationRepository().read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a comparison operation.", e);
        }
    }

    @Override
    public CompletableFuture<ComparisonOperationEntity> updateAsync(ComparisonOperationEntity entity) throws BusinessException {
        try {
            return this.operationRepository.getComparisonOperationRepository().updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating a comparison operation.", e);
        }

    }

    @Override
    public ComparisonOperationEntity updateSync(ComparisonOperationEntity entity) throws BusinessException {
        try {
            return this.operationRepository.getComparisonOperationRepository().updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating a comparison operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.operationRepository.getComparisonOperationRepository().deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting a comparison operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.operationRepository.getComparisonOperationRepository().deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting a comparison operation.", e);
        }
    }

    @Override
    public Optional<List<ComparisonOperationEntity>> readAll() throws BusinessException {
        try {
            return this.operationRepository.getComparisonOperationRepository().findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all comparison operations.", e);
        }
    }

    @Override
    public Optional<List<ComparisonOperationEntity>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.operationRepository.getComparisonOperationRepository().findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all comparison operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.operationRepository.getComparisonOperationRepository().check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a comparison operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.operationRepository.getComparisonOperationRepository().checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all comparison operations.", e);
        }
    }
}
