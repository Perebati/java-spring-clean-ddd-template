package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonCustomListRepository;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree.node.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListServiceImpl
 * @since v1.0 (06/02/2025)
 */
@Service
class ComparisonCustomListServiceImpl
        extends GenericServiceImpl<ComparisonCustomList, ComparisonCustomListRepository>
        implements ComparisonCustomListService {
    private final NodeService nodeService;
    private final ComparisonFactory comparisonFactory;
    private final NodeTreeService<ComparisonCustomList> nodeTreeService;

    @Autowired
    protected ComparisonCustomListServiceImpl(
            ComparisonCustomListRepository repository,
            NodeService nodeService,
            ComparisonFactory comparisonFactory,
            NodeTreeService<ComparisonCustomList> nodeTreeService) {
        super(repository);
        this.nodeService = nodeService;
        this.comparisonFactory = comparisonFactory;
        this.nodeTreeService = nodeTreeService;
    }

    public ComparisonCustomList create(
            ComparisonCustomList.ComparisonCustomListEnum type,
            String jsonPath,
            UUID customListId,
            UUID parentId,
            NodeTreeIf.SetNode position
    ) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(parentId);
            ComparisonCustomList newOperation = this.comparisonFactory.produce(type, jsonPath, customListId, parent);
            return this.nodeTreeService.createNode(newOperation, parent, position);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a comparison operation.");
        }
    }
}