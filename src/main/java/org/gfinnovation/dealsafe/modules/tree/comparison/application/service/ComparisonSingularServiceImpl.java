package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles comparisons operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationBusinessImpl
 * @since 30/10/2024
 */

@Service
class ComparisonSingularServiceImpl
        extends GenericServiceImpl<ComparisonSingular, ComparisonSingularRepository>
        implements ComparisonSingularService {
    private final ComparisonFactory comparisonOperationFactory;
    private final NodeRepository nodeRepository;
    private final NodeTreeRepository<ComparisonSingular> comparisonSingularRepository;

    @Autowired
    public ComparisonSingularServiceImpl(
            ComparisonSingularRepository comparisonSingularRepository,
            ComparisonFactory comparisonOperationFactory,
            NodeRepository nodeRepository,
            NodeTreeRepository<ComparisonSingular> comparisonSingularRepository1
    ) {
        super(comparisonSingularRepository);
        this.comparisonOperationFactory = comparisonOperationFactory;
        this.nodeRepository = nodeRepository;
        this.comparisonSingularRepository = comparisonSingularRepository1;
    }

    @Override
    public ComparisonSingular create(
            ComparisonSingularRecord input
    ) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeRepository.read(input.parentId(), getRepositoryAuth());
            ComparisonSingular newOperation = this.comparisonOperationFactory.produce(input.type(), input.jsonPath(), input.variable(), parent);
            return this.comparisonSingularRepository.createNode(newOperation, parent, input.position(), getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Business: Something went wrong creating a comparison operation.");
        }
    }
}