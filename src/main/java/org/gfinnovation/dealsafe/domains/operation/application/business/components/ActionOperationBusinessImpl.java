package org.gfinnovation.dealsafe.domains.operation.application.business.components;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ActionOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces.ComparisonOperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.interfaces.OperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.repository.OperationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Handles action operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationImpl
 * @since 30/10/2024
 */
@Component
@RequiredArgsConstructor
public class ActionOperationBusinessImpl implements ActionOperationBusiness {
    private final OperationFactory operationFactory;
    private final ComparisonOperationBusiness comparisonOperationBusiness;
    private final OperationRepository operationRepository;

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
    public ActionOperationEntity create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException, FactoryException, ValidationException, RepositoryException {
        try {
            ComparisonOperationEntity operation = this.comparisonOperationBusiness.read(operation_id).orElseThrow(() -> new EntityNotFoundException("Operation not found!"));
            ActionOperationEntity newOperationAction = this.operationFactory.getActionOperationFactory().produce(user_id, company_id, url, message);
            operation.addAction(newOperationAction);
            this.comparisonOperationBusiness.update(operation);
            return newOperationAction;
        } catch (Exception e) {
            throw new BusinessException("Something went wrong creating an action operation", e);
        }
    }

    @Override
    public Optional<ActionOperationEntity> read(UUID id) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().read(id);
    }

    @Override
    public ActionOperationEntity update(ActionOperationEntity entity) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().update(entity);
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().delete(id);
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAll() throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().findAll();
    }

    @Override
    public Optional<List<ActionOperationEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return this.operationRepository.getActionOperationRepository().findAllByIds(ids);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        this.operationRepository.getActionOperationRepository().checkAll(ids);
    }
}
