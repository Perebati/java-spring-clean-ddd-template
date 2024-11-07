package org.gfinnovation.dealsafe.domains.operation.application.business.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ActionOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ComparisonOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.interfaces.OperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.OperationRepository;
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
public class ActionOperationBusinessImpl extends GenericBusinessImpl implements ActionOperationBusiness {
    private final OperationFactory operationFactory;
    private final ComparisonOperationBusiness comparisonOperationBusiness;
    private final OperationRepository operationRepository;

    @Autowired
    public ActionOperationBusinessImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            OperationFactory operationFactory,
            ComparisonOperationBusiness comparisonOperationBusiness,
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
     * @throws BusinessException   When an error occurs on business level.
     * @throws FactoryException    When an error occurs on factory level.
     * @throws ValidationException When an error occurs on factory level.
     * @throws RepositoryException When an error occurs on repository level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public ActionOperationEntity create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            ComparisonOperationEntity operation = this.comparisonOperationBusiness.read(operation_id);
            ActionOperationEntity newOperationAction = this.operationFactory.getActionOperationFactory().produce(user_id, company_id, url, message);
            operation.addAction(newOperationAction);
            this.comparisonOperationBusiness.updateSync(operation);
            return newOperationAction;
        } catch (Exception e) {
            throw new BusinessException("Something went wrong creating an action operation", e);
        }
    }

    @Override
    public ActionOperationEntity read(UUID id) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().read(getUserId(), getCompanyId(), id);
    }

    @Override
    public CompletableFuture<ActionOperationEntity> updateAsync(ActionOperationEntity entity) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().updateAsync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public ActionOperationEntity updateSync(ActionOperationEntity entity) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().updateSync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public void deleteAsync(UUID id) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().deleteAsync(getUserId(), getCompanyId(), id);
    }

    @Override
    public void deleteSync(UUID id) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().deleteSync(getUserId(), getCompanyId(), id);
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAll() throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().findAll(getUserId(), getCompanyId());
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().findAllByIds(getUserId(), getCompanyId(), ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().check(getUserId(), getCompanyId(), id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().checkAll(getUserId(), getCompanyId(), ids);
    }
}
