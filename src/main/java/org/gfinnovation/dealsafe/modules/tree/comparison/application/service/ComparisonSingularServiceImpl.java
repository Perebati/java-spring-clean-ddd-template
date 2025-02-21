package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonSingularRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonSingularService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles Writing/Reading operations of a comparison that uses a single variable for comparison.
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
    private final NodeService nodeService;
    private final RootTreeService rootTreeService;
    private final NodeTreeService<ComparisonSingular> nodeTreeService;

    private final ComparisonFactory comparisonOperationFactory;

    @Autowired
    public ComparisonSingularServiceImpl(
            ComparisonSingularRepository comparisonSingularRepository,
            ComparisonFactory comparisonOperationFactory,
            NodeService nodeService, RootTreeService rootTreeService,
            NodeTreeService<ComparisonSingular> nodeTreeService
    ) {
        super(comparisonSingularRepository);
        this.comparisonOperationFactory = comparisonOperationFactory;
        this.nodeService = nodeService;
        this.rootTreeService = rootTreeService;
        this.nodeTreeService = nodeTreeService;
    }

    /**
     * Handles creation of a comparison that operates upon a singular variable.
     *
     * @param request Record containing all data for Comparison creation.
     * @return ComparisonSingular
     * @throws SystemGlobalException Standard DealSafe error.
     */
    @Override
    public ComparisonSingular createComparison(ComparisonSingularRecord request,
                                               Boolean keepHistory) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(request.parentId());
            ComparisonSingular newOperation = this.comparisonOperationFactory.produce(
                    request.type(),
                    request.jsonPath(),
                    request.variable(),
                    parent);
            newOperation = this.nodeTreeService.createNode(newOperation, parent, request.position());
            if(keepHistory) this.rootTreeService.keepHistory(newOperation.getId());
            return newOperation;
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a comparison operation.", e);
        }
    }
}