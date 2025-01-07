package org.gfinnovation.dealsafe.modules.input.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe._shared.utils.pagination.PageRequestDTO;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.modules.input.domain.repository.InputRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
class InputServiceImpl extends GenericServiceImpl implements InputService {
    private final InputRepository inputRepository;
    private final InputFactory inputFactory;

    @Autowired
    public InputServiceImpl(
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
     * @throws BusinessException Thrown when ac error occurred on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    @Override
    public InputEntity create(String name, String json) throws BusinessException {
        try {
            return this.inputRepository.createAsync(this.inputFactory.produce(getUserId(), getCompanyId(), name, json), getRepositoryAuth()).join();
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong creating an input.", e);
        }
    }

    @Override
    public InputEntity read(UUID id) throws BusinessException {
        try {
            return this.inputRepository.read(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading an input.", e);
        }
    }

    @Override
    public CompletableFuture<InputEntity> updateAsync(InputEntity entity) throws BusinessException {
        try {
            return this.inputRepository.updateAsync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async updating an input.", e);
        }
    }

    @Override
    public InputEntity updateSync(InputEntity entity) throws BusinessException {
        try {
            return this.inputRepository.updateSync(entity, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong updating an input.", e);
        }
    }

    @Override
    public void deleteAsync(UUID id) throws BusinessException {
        try {
            this.inputRepository.deleteAsync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong async deleting an input.", e);
        }
    }

    @Override
    public void deleteSync(UUID id) throws BusinessException {
        try {
            this.inputRepository.deleteSync(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong deleting an input.", e);
        }
    }

    @Override
    public Optional<List<InputEntity>> readAll() throws BusinessException {
        try {
            return this.inputRepository.findAll(getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all inputs.", e);
        }
    }

    @Override
    public Optional<List<InputEntity>> readAllByIds(List<UUID> ids) throws BusinessException {
        try {
            return this.inputRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all inputs by id.", e);
        }
    }

    @Override
    public void check(UUID id) throws BusinessException {
        try {
            this.inputRepository.check(id, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking an input.", e);
        }
    }

    @Override
    public void checkAll(Set<UUID> ids) throws BusinessException {
        try {
            this.inputRepository.checkAll(ids, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong checking all inputs by ids.", e);
        }
    }

    public Page<InputEntity> readAllPaginated(PageRequestDTO<NodeTreeEntity.SortField> pageRequestDTO) throws BusinessException {
        try {
            return this.inputRepository.findAllPaginated(pageRequestDTO, getRepositoryAuth());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Business: Something went wrong reading all inputs paginated.", e);
        }
    }
}