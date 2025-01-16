package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.service.interfaces.ComparisonOperationService;
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
    private final ComparisonOperationService comparisonOperationBusiness;
    private final ActionOperationRepository actionOperationRepository;

    @Autowired
    public ActionOperationServiceImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            ActionOperationFactory actionOperationFactory,
            ComparisonOperationService comparisonOperationBusiness,
            ActionOperationRepository actionOperationRepository
            ) {
        super(userBusiness, companyBusiness);
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
     * @throws BusinessException When an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public ActionOperation create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException {
        try {
            ComparisonOperation operation = this.comparisonOperationBusiness.read(operation_id);
            ActionOperation newOperationAction = this.actionOperationFactory.produce(url, message);
            operation.addAction(newOperationAction);
            this.comparisonOperationBusiness.updateSync(operation);
            return newOperationAction;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking an action operation.", e);
        }
    }

    @Override
    public ActionOperation read(UUID id) throws BusinessException {
        try {
            return this.actionOperationRepository.read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading an action operation.", e);
        }
    }

    @Override
    public CompletableFuture<ActionOperation> updateAsync(ActionOperation entity) throws BusinessException {
        try {
            return this.actionOperationRepository.updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating an action operation.", e);
        }
    }

    @Override
    public ActionOperation updateSync(ActionOperation entity) throws BusinessException {
        try {
            return this.actionOperationRepository.updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating an action operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.actionOperationRepository.deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting an action operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.actionOperationRepository.deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting an action operation.", e);
        }
    }

    @Override
    public Optional<List<ActionOperation>> readAll() throws BusinessException {
        try {
            return this.actionOperationRepository.findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all action operations.", e);
        }
    }

    @Override
    public Optional<List<ActionOperation>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.actionOperationRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all action operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.actionOperationRepository.check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking an action operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.actionOperationRepository.checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all action operations.", e);
        }
    }
}
