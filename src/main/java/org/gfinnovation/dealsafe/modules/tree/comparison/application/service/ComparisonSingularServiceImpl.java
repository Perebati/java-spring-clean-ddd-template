package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree._shared.application.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles comparisons operations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ComparisonOperationServiceImpl
 * @since v1.0 (30/11/2024)
 */
@Service
class ComparisonSingularServiceImpl
        extends GenericServiceImpl<ComparisonSingular, ComparisonSingularRepository>
        implements ComparisonSingularService {
    private final ComparisonFactory comparisonOperationFactory;
    private final NodeService nodeService;
    private final NodeTreeService<ComparisonSingular> nodeTreeService;

    @Autowired
    public ComparisonSingularServiceImpl(
            ComparisonSingularRepository comparisonSingularRepository,
            ComparisonFactory comparisonOperationFactory,
            NodeService nodeService,
            NodeTreeService<ComparisonSingular> nodeTreeService
    ) {
        super(comparisonSingularRepository);
        this.comparisonOperationFactory = comparisonOperationFactory;
        this.nodeService = nodeService;
        this.nodeTreeService = nodeTreeService;
    }

    @Override
    public ComparisonSingular create(
            ComparisonSingularRecord input
    ) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(input.parentId());
            ComparisonSingular newOperation = this.comparisonOperationFactory.produce(input.type(), input.jsonPath(), input.variable(), parent);
            return this.nodeTreeService.createNode(newOperation, parent, input.position());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a comparison operation.");
        }
    }
}