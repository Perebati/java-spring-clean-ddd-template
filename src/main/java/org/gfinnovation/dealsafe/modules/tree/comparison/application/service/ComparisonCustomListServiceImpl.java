package org.gfinnovation.dealsafe.modules.tree.comparison.application.service;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.application.service.interfaces.ComparisonCustomListService;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonCustomListRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class ComparisonCustomListServiceImpl
        extends GenericServiceImpl<ComparisonCustomList, ComparisonCustomListRepository>
        implements ComparisonCustomListService {
    private final NodeRepository nodeRepository;
    private final ComparisonFactory comparisonFactory;
    private final NodeTreeRepository<ComparisonCustomList> nodeTreeRepository;

    @Autowired
    protected ComparisonCustomListServiceImpl(
            ComparisonCustomListRepository repository,
            NodeRepository nodeRepository,
            ComparisonFactory comparisonFactory,
            NodeTreeRepository<ComparisonCustomList> nodeTreeRepository) {
        super(repository);
        this.nodeRepository = nodeRepository;
        this.comparisonFactory = comparisonFactory;
        this.nodeTreeRepository = nodeTreeRepository;
    }

    public ComparisonCustomList create(
            ComparisonCustomList.ComparisonCustomListEnum type,
            String jsonPath,
            UUID customListId,
            UUID parentId,
            NodeTreeIf.SetNode position
    ) throws ServiceException, BadRequestException {
        try {
            Node<?> parent = this.nodeRepository.read(parentId, getRepositoryAuth());
            ComparisonCustomList newOperation = this.comparisonFactory.produce(type, jsonPath, customListId, parent);
            return this.nodeTreeRepository.createNode(newOperation, parent, position, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }
}