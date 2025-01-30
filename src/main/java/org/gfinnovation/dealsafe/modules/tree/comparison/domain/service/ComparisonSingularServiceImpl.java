package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.repository.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeBlockService;
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
public class ComparisonSingularServiceImpl extends GenericServiceImpl implements ComparisonSingularService {
    private final NodeTreeBlockService nodeTreeBlockService;
    private final ComparisonSingularRepository comparisonSingularRepository;
    private final ComparisonFactory comparisonOperationFactory;

    public ComparisonSingularServiceImpl(
            NodeTreeBlockService nodeTreeBlockService,
            ComparisonSingularRepository comparisonSingularRepository,
            ComparisonFactory comparisonOperationFactory
    ) {
        this.nodeTreeBlockService = nodeTreeBlockService;
        this.comparisonSingularRepository = comparisonSingularRepository;
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
    public ComparisonSingular create(ComparisonSingular.ComparisonSingularTypeEnum type, String jsonPath, String variable, UUID node_id) throws ServiceException, BadRequestException {
        try {
            NodeTree<?> parent = this.nodeTreeBlockService.read(node_id);
            ComparisonSingular newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variable, parent);
            return this.comparisonSingularRepository.createSingComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }

    @Override
    public ComparisonSingular read(UUID id) throws ServiceException {
        try {
            return this.comparisonSingularRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a comparison operation.", e);
        }
    }

    @Override
    public CompletableFuture<ComparisonSingular> updateAsync(ComparisonSingular entity) throws ServiceException {
        try {
            return this.comparisonSingularRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a comparison operation.", e);
        }

    }

    @Override
    public ComparisonSingular updateSync(ComparisonSingular entity) throws ServiceException {
        try {
            return this.comparisonSingularRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a comparison operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.comparisonSingularRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a comparison operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.comparisonSingularRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a comparison operation.", e);
        }
    }

    @Override
    public Optional<List<ComparisonSingular>> readAll() throws ServiceException {
        try {
            return this.comparisonSingularRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations.", e);
        }
    }

    @Override
    public Optional<List<ComparisonSingular>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.comparisonSingularRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.comparisonSingularRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a comparison operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.comparisonSingularRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all comparison operations.", e);
        }
    }
}