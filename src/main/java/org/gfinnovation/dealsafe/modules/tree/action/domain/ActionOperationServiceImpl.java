package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.modules.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.service.interfaces.ComparisonSingularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Handles action operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationImpl
 * @since 30/10/2024
 */
@Component
public class ActionOperationServiceImpl extends GenericServiceImpl implements ActionOperationService {
    private final ActionOperationFactory actionOperationFactory;
    private final ComparisonSingularService comparisonOperationBusiness;
    private final ActionOperationRepository actionOperationRepository;

    @Autowired
    public ActionOperationServiceImpl(
            ActionOperationFactory actionOperationFactory,
            ComparisonSingularService comparisonOperationBusiness,
            ActionOperationRepository actionOperationRepository
    ) {
        this.actionOperationFactory = actionOperationFactory;
        this.comparisonOperationBusiness = comparisonOperationBusiness;
        this.actionOperationRepository = actionOperationRepository;
    }


    /**
     * Creates an action linked to a comparison operation.
     *
     * @param user_id      Userid.
     * @param company_id   CompanyId.
     * @param url          Target message url.
     * @param message      Message given.
     * @param operation_id Parent operation.
     * @return ActionOperation
     * @throws ServiceException When an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public ActionOperation create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws ServiceException {
        try {
//            Comparison operation = this.comparisonOperationBusiness.read(operation_id);
//            ActionOperation newOperationAction = this.actionOperationFactory.produce(url, message);
//            operation.addAction(newOperationAction);
//            this.comparisonOperationBusiness.updateSync(operation);
//            return newOperationAction;
            return null;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking an action operation.", e);
        }
    }

    @Override
    public ActionOperation read(UUID id) throws ServiceException {
        try {
            return this.actionOperationRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading an action operation.", e);
        }
    }

    @Override
    public CompletableFuture<ActionOperation> updateAsync(ActionOperation entity) throws ServiceException {
        try {
            return this.actionOperationRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating an action operation.", e);
        }
    }

    @Override
    public ActionOperation updateSync(ActionOperation entity) throws ServiceException {
        try {
            return this.actionOperationRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating an action operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.actionOperationRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting an action operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.actionOperationRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting an action operation.", e);
        }
    }

    @Override
    public Optional<List<ActionOperation>> readAll() throws ServiceException {
        try {
            return this.actionOperationRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all action operations.", e);
        }
    }

    @Override
    public Optional<List<ActionOperation>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.actionOperationRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all action operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws ServiceException {
        try {
            this.actionOperationRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking an action operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.actionOperationRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all action operations.", e);
        }
    }
}
