package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.repository.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces.ComparisonMultiDomainService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeBlockDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class ComparisonMultiDomainServiceImpl
        extends GenericDomainServiceImpl<ComparisonMulti, ComparisonMultiRepository>
        implements ComparisonMultiDomainService {
    private final NodeTreeBlockDomainService nodeTreeBlockDomainService;
    private final ComparisonFactory comparisonOperationFactory;

    @Autowired
    public ComparisonMultiDomainServiceImpl(
            NodeTreeBlockDomainService nodeTreeBlockDomainService,
            ComparisonMultiRepository comparisonMultiRepository,
            ComparisonFactory comparisonOperationFactory
    ) {
        super(comparisonMultiRepository);
        this.nodeTreeBlockDomainService = nodeTreeBlockDomainService;
        this.comparisonOperationFactory = comparisonOperationFactory;
    }

    public ComparisonMulti create(ComparisonMulti.ComparisonMultiTypeEnum type, String jsonPath, List<String> variables, UUID node_id) throws ServiceException, BadRequestException {
        try {
            NodeTree<?> parent = this.nodeTreeBlockDomainService.read(node_id);
            ComparisonMulti newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variables, parent);
            return this.repository.createMultiComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }
}