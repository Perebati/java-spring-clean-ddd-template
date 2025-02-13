package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMultiServiceImpl
 * @since v1.0 (06/02/2025)
 */
@Service
class ComparisonMultiServiceImpl
        extends GenericServiceImpl<ComparisonMulti, ComparisonMultiRepository>
        implements ComparisonMultiService {
    private final ComparisonFactory comparisonOperationFactory;
    private final NodeService nodeService;
    private final NodeTreeService<ComparisonMulti> nodeTreeService;

    @Autowired
    public ComparisonMultiServiceImpl(
            ComparisonMultiRepository comparisonMultiRepository,
            ComparisonFactory comparisonOperationFactory,
            NodeService nodeService,
            NodeTreeService<ComparisonMulti> nodeTreeService
    ) {
        super(comparisonMultiRepository);
        this.comparisonOperationFactory = comparisonOperationFactory;
        this.nodeService = nodeService;
        this.nodeTreeService = nodeTreeService;
    }

    public ComparisonMulti create(
            ComparisonMultiRecord input
    ) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(input.parentId());
            ComparisonMulti newOperation = this.comparisonOperationFactory.produce(
                    input.type(),
                    input.jsonPath(),
                    input.variables(),
                    parent);
            return this.nodeTreeService.createNode(newOperation, parent, input.position());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a comparison operation.");
        }
    }
}