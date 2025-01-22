package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.service;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.Comparison;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.factory.interfaces.ComparisonOperationFactory;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.enums.ComparisonTypeEnum;
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
     * @return Comparison
     * @throws ServiceException    Thrown when an error occurred on business level.
     * @throws FactoryException    Thrown when an error occurred on factory level.
     * @throws ValidationException Thrown when an error occurred on factory level.
     * @throws RepositoryException Thrown when an error occurred on repository level.
     * @throws BadRequestException Thrown when user input is not correct.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public Comparison create(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws ServiceException, BadRequestException {
        try {
            NodeTree parent = this.nodeTreeService.read(node_id);
            Comparison newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variable, node_id);
            return this.comparisonOperationRepository.createComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }

    @Override
    public Comparison read(UUID id) throws ServiceException {
        try {
            return this.comparisonOperationRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a comparison operation.", e);
        }
    }

    @Override
    public CompletableFuture<Comparison> updateAsync(Comparison entity) throws ServiceException {
        try {
            return this.comparisonOperationRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a comparison operation.", e);
        }

    }

    @Override
    public Comparison updateSync(Comparison entity) throws ServiceException {
        try {
            return this.comparisonOperationRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a comparison operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.comparisonOperationRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a comparison operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.comparisonOperationRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a comparison operation.", e);
        }
    }

    @Override
    public Optional<List<Comparison>> readAll() throws ServiceException {
        try {
            return this.comparisonOperationRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations.", e);
        }
    }

    @Override
    public Optional<List<Comparison>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.comparisonOperationRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.comparisonOperationRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a comparison operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.comparisonOperationRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all comparison operations.", e);
        }
    }
}