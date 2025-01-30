package org.gfinnovation.dealsafe.modules.tree.comparison.domain.service;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.repository.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.service.interfaces.ComparisonMultiService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ComparisonMultiServiceImpl extends GenericServiceImpl implements ComparisonMultiService {
    private final NodeTreeService nodeTreeService;
    private final ComparisonMultiRepository comparisonMultiRepository;
    private final ComparisonFactory comparisonOperationFactory;

    public ComparisonMultiServiceImpl(
            NodeTreeService nodeTreeService,
            ComparisonMultiRepository comparisonMultiRepository,
            ComparisonFactory comparisonOperationFactory
    ) {
        this.nodeTreeService = nodeTreeService;
        this.comparisonMultiRepository = comparisonMultiRepository;
        this.comparisonOperationFactory = comparisonOperationFactory;
    }

    public ComparisonMulti create(ComparisonMulti.ComparisonMultiTypeEnum type, String jsonPath, List<String> variables, UUID node_id) throws ServiceException, BadRequestException {
        try {
            NodeTree<?> parent = this.nodeTreeService.read(node_id);
            ComparisonMulti newOperation = this.comparisonOperationFactory.produce(type, jsonPath, variables, parent);
            return this.comparisonMultiRepository.createMultiComparison(newOperation, parent, getRepositoryAuth());
        } catch (BadRequestException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a comparison operation.", e);
        }
    }

    @Override
    public ComparisonMulti read(UUID id) throws ServiceException {
        try {
            return this.comparisonMultiRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading a comparison operation.", e);
        }
    }

    @Override
    public CompletableFuture<ComparisonMulti> updateAsync(ComparisonMulti entity) throws ServiceException {
        try {
            return this.comparisonMultiRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating a comparison operation.", e);
        }

    }

    @Override
    public ComparisonMulti updateSync(ComparisonMulti entity) throws ServiceException {
        try {
            return this.comparisonMultiRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating a comparison operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.comparisonMultiRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting a comparison operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.comparisonMultiRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting a comparison operation.", e);
        }
    }

    @Override
    public Optional<List<ComparisonMulti>> readAll() throws ServiceException {
        try {
            return this.comparisonMultiRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations.", e);
        }
    }

    @Override
    public Optional<List<ComparisonMulti>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.comparisonMultiRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all comparison operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.comparisonMultiRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking a comparison operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.comparisonMultiRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all comparison operations.", e);
        }
    }
}