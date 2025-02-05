package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonMultiRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ComparisonMultiServiceImpl
        extends GenericServiceImpl<ComparisonMulti, ComparisonMultiRepository>
        implements ComparisonMultiService {
    private final ComparisonFactory comparisonOperationFactory;
    private final NodeRepository nodeRepository;
    private final NodeTreeRepository<ComparisonMulti> nodeTreeRepository;

    @Autowired
    public ComparisonMultiServiceImpl(
            ComparisonMultiRepository comparisonMultiRepository,
            ComparisonFactory comparisonOperationFactory,
            NodeRepository nodeRepository,
            NodeTreeRepository<ComparisonMulti> nodeTreeRepository
    ) {
        super(comparisonMultiRepository);
        this.comparisonOperationFactory = comparisonOperationFactory;
        this.nodeRepository = nodeRepository;
        this.nodeTreeRepository = nodeTreeRepository;
    }

    public ComparisonMulti create(
            ComparisonMultiRecord input
    ) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeRepository.read(input.parentId(), getRepositoryAuth());
            ComparisonMulti newOperation = this.comparisonOperationFactory.produce(input.type(), input.jsonPath(), input.variables(), parent);
            return this.nodeTreeRepository.createNode(newOperation, parent, input.position(), getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Business: Something went wrong creating a comparison operation.");
        }
    }
}