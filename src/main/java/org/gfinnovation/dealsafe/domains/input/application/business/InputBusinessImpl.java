package org.gfinnovation.dealsafe.domains.input.application.business;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.entity.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.domains.input.entity.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * This class handles dynamic input structures for a validation tree.
 * Inputs can be dynamic or predefined.
 * If the input is dynamic, it passes through this business class.
 * There's no business class for static input, all validations for that
 * type of input is done inside an enumerator.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusinessImpl
 * @since 30/10/2024
 */

@Service
class InputBusinessImpl extends GenericBusinessImpl implements InputBusiness {
    private final InputRepository inputRepository;
    private final InputFactory inputFactory;

    @Autowired
    public InputBusinessImpl(
            UserBusiness userBusiness,
            CompanyBusiness companyBusiness,
            InputRepository inputRepository,
            InputFactory inputFactory) {
        super(userBusiness, companyBusiness);
        this.inputRepository = inputRepository;
        this.inputFactory = inputFactory;
    }

    /**
     * Handles the creation of dynamic input structures.
     *
     * @param name Name of given input.
     * @param json Example of json to map.
     * @return InputEntity
     * @throws FactoryException    Thrown when ac error occurred on factory level.
     * @throws ValidationException Thrown when ac error occurred on factory level.
     * @throws RepositoryException Thrown when ac error occurred on repository level.
     * @throws BusinessException   Thrown when ac error occurred on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public InputEntity create(String name, String json) throws FactoryException, ValidationException, RepositoryException, BusinessException {
        try {
            return this.inputRepository.createAsync(getUserId(), getCompanyId(), this.inputFactory.produce(getUserId(), getCompanyId(), name, json)).join();
        } catch (Exception e) {
            throw new BusinessException("Something went wrong creating an input.", e);
        }
    }

    @Override
    public InputEntity read(UUID id) {
        return this.inputRepository.read(getUserId(), getCompanyId(), id);
    }

    @Override
    public CompletableFuture<InputEntity> updateAsync(InputEntity entity) {
        return this.inputRepository.updateAsync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public InputEntity updateSync(InputEntity entity) {
        return this.inputRepository.updateSync(getUserId(), getCompanyId(), entity);
    }

    @Override
    public void deleteAsync(UUID id) {
        this.inputRepository.deleteAsync(getUserId(), getCompanyId(), id);
    }

    @Override
    public void deleteSync(UUID id) {
        this.inputRepository.deleteSync(getUserId(), getCompanyId(), id);
    }

    @Override
    public Optional<List<InputEntity>> readAll() {
        return this.inputRepository.findAll(getUserId(), getCompanyId());
    }

    @Override
    public Optional<List<InputEntity>> readAllByIds(List<UUID> ids) {
        return this.inputRepository.findAllByIds(getUserId(), getCompanyId(), ids);
    }

    @Override
    public void check(UUID id) {
        this.inputRepository.check(getUserId(), getCompanyId(), id);
    }

    @Override
    public void checkAll(Set<UUID> ids) {
        this.inputRepository.checkAll(getUserId(), getCompanyId(), ids);
    }
}