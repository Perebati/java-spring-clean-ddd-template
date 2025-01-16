package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.service;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.factory.interfaces.ComparisonOperationFactory;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.repository.ComparisonOperationRepository;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.service.interfaces.ComparisonOperationService;
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
public class ComparisonOperationServiceImpl extends GenericServiceImpl implements ComparisonOperationService {
    private final NodeTreeService nodeTreeService;
    private final ComparisonOperationRepository comparisonOperationRepository;
    private final ComparisonOperationFactory comparisonOperationFactory;

    public ComparisonOperationServiceImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            NodeTreeService nodeTreeService,
            ComparisonOperationRepository comparisonOperationRepository,
            ComparisonOperationFactory comparisonOperationFactory
            ) {
        super(userBusiness, companyBusiness);
        this.nodeTreeService = nodeTreeService;
        this.comparisonOperationRepository = comparisonOperationRepository;
        this.comparisonOperationFactory = comparisonOperationFactory;
    }

    /**
     * Creates a comparison operation.
     *
     * @param type     Type of comparison.
     * @param jsonPath Path to the compared variable.
     * @param variable Variable to compare.
     * @param node_id  Parent node.
     * @return ComparisonOperation
     * @throws BusinessException   Thrown when an error occurred on business level.
     * @throws FactoryException    Thrown when an error occurred on factory level.
     * @throws ValidationException Thrown when an error occurred on factory level.
     * @throws RepositoryException Thrown when an error occurred on repository level.
     * @throws BadRequestException Thrown when user input is not correct.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public ComparisonOperation create(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws BusinessException, BadRequestException {
        try {
            NodeTree parent = this.nodeTreeService.read(node_id);
            ComparisonOperation newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variable, node_id);
            return this.comparisonOperationRepository.createComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong creating a comparison operation.", e);
        }
    }

    @Override
    public ComparisonOperation read(UUID id) throws BusinessException {
        try {
            return this.comparisonOperationRepository.read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading a comparison operation.", e);
        }
    }

    @Override
    public CompletableFuture<ComparisonOperation> updateAsync(ComparisonOperation entity) throws BusinessException {
        try {
            return this.comparisonOperationRepository.updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating a comparison operation.", e);
        }

    }

    @Override
    public ComparisonOperation updateSync(ComparisonOperation entity) throws BusinessException {
        try {
            return this.comparisonOperationRepository.updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating a comparison operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.comparisonOperationRepository.deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting a comparison operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.comparisonOperationRepository.deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting a comparison operation.", e);
        }
    }

    @Override
    public Optional<List<ComparisonOperation>> readAll() throws BusinessException {
        try {
            return this.comparisonOperationRepository.findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all comparison operations.", e);
        }
    }

    @Override
    public Optional<List<ComparisonOperation>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.comparisonOperationRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all comparison operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.comparisonOperationRepository.check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking a comparison operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.comparisonOperationRepository.checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all comparison operations.", e);
        }
    }
}
