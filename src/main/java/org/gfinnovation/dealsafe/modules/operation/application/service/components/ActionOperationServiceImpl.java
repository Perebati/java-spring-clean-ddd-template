package org.gfinnovation.dealsafe.modules.operation.application.service.components;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.operation.application.service.components.interfaces.ActionOperationService;
import org.gfinnovation.dealsafe.modules.operation.application.service.components.interfaces.ComparisonOperationService;
import org.gfinnovation.dealsafe.modules.operation.domain.ActionOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.domain.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.domain.factory.interfaces.OperationFactory;
import org.gfinnovation.dealsafe.modules.operation.domain.repository.OperationRepository;
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
    private final OperationFactory operationFactory;
    private final ComparisonOperationService comparisonOperationBusiness;
    private final OperationRepository operationRepository;

    @Autowired
    public ActionOperationServiceImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            OperationFactory operationFactory,
            ComparisonOperationService comparisonOperationBusiness,
            OperationRepository operationRepository) {
        super(userBusiness, companyBusiness);
        this.operationFactory = operationFactory;
        this.comparisonOperationBusiness = comparisonOperationBusiness;
        this.operationRepository = operationRepository;
    }


    /**
     * Creates an action linked to a comparison operation.
     *
     * @param user_id      Userid.
     * @param company_id   CompanyId.
     * @param url          Target message url.
     * @param message      Message given.
     * @param operation_id Parent operation.
     * @return ActionOperationEntity
     * @throws BusinessException When an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public ActionOperationEntity create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException {
        try {
            ComparisonOperationEntity operation = this.comparisonOperationBusiness.read(operation_id);
            ActionOperationEntity newOperationAction = this.operationFactory.getActionOperationFactory().produce(url, message);
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
    public ActionOperationEntity read(UUID id) throws BusinessException {
        try {
            return this.operationRepository.getActionOperationRepository().read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading an action operation.", e);
        }
    }

    @Override
    public CompletableFuture<ActionOperationEntity> updateAsync(ActionOperationEntity entity) throws BusinessException {
        try {
            return this.operationRepository.getActionOperationRepository().updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating an action operation.", e);
        }
    }

    @Override
    public ActionOperationEntity updateSync(ActionOperationEntity entity) throws BusinessException {
        try {
            return this.operationRepository.getActionOperationRepository().updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating an action operation.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.operationRepository.getActionOperationRepository().deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting an action operation.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.operationRepository.getActionOperationRepository().deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting an action operation.", e);
        }
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAll() throws BusinessException {
        try {
            return this.operationRepository.getActionOperationRepository().findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all action operations.", e);
        }
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.operationRepository.getActionOperationRepository().findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all action operations by ids.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.operationRepository.getActionOperationRepository().check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking an action operation.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.operationRepository.getActionOperationRepository().checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all action operations.", e);
        }
    }
}
