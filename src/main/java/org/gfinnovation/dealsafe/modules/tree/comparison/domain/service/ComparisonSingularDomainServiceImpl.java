package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces.ComparisonSingularDomainService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeBlockDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Handles comparisons operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationBusinessImpl
 * @since 30/10/2024
 */

@Service
class ComparisonSingularDomainServiceImpl
        extends GenericDomainServiceImpl<ComparisonSingular, ComparisonSingularRepository>
        implements ComparisonSingularDomainService {
    private final NodeTreeBlockDomainService nodeTreeBlockDomainService;
    private final ComparisonFactory comparisonOperationFactory;

    @Autowired
    public ComparisonSingularDomainServiceImpl(
            NodeTreeBlockDomainService nodeTreeBlockDomainService,
            ComparisonSingularRepository comparisonSingularRepository,
            ComparisonFactory comparisonOperationFactory
    ) {
        super(comparisonSingularRepository);
        this.nodeTreeBlockDomainService = nodeTreeBlockDomainService;
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
            NodeTree<?> parent = this.nodeTreeBlockDomainService.read(node_id);
            ComparisonSingular newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variable, parent);
            return this.repository.createSingComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }
}