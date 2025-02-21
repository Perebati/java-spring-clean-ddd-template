package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.comparison.adpter.web.request.ComparisonCustomListRecord;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonCustomListRepository;
import org.springframework.stereotype.Service;

/**
 * Handles Writing/Reading operations of a comparison that uses a custom list.
 * This can be useful if the custom list is, for example, a blacklist.
 *
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
    private final RootTreeService rootTreeService;
    private final NodeTreeService<ComparisonCustomList> nodeTreeService;

    private final ComparisonFactory comparisonFactory;

    protected ComparisonCustomListServiceImpl(ComparisonCustomListRepository repository,
                                              NodeService nodeService,
                                              RootTreeService rootTreeService,
                                              NodeTreeService<ComparisonCustomList> nodeTreeService,
                                              ComparisonFactory comparisonFactory) {
        super(repository);
        this.nodeService = nodeService;
        this.rootTreeService = rootTreeService;
        this.nodeTreeService = nodeTreeService;
        this.comparisonFactory = comparisonFactory;
    }

    /**
     * Handles creation of a comparison that operates upon a custom list of variables.
     *
     * @param request Record containing all data for Comparison creation.
     * @return ComparisonCustomList
     * @throws SystemGlobalException Standard DealSafe error.
     */
    @Override
    @Transactional
    public ComparisonCustomList createComparison(ComparisonCustomListRecord request,
                                                 Boolean keepHistory) throws SystemGlobalException {
        try {
            Node<?> parent = this.nodeService.read(request.parentId());
            ComparisonCustomList newOperation = this.comparisonFactory.produce(
                    request.type(),
                    request.jsonPath(),
                    request.comparisonListId(),
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