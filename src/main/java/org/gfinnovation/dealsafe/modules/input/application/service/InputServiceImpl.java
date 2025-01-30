package org.gfinnovation.dealsafe.modules.input.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.modules.input.domain.repository.InputRepository;
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
class InputServiceImpl extends GenericServiceImpl implements InputService {
    private final InputRepository inputRepository;
    private final InputFactory inputFactory;

    @Autowired
    public InputServiceImpl(
            InputRepository inputRepository,
            InputFactory inputFactory) {
        this.inputRepository = inputRepository;
        this.inputFactory = inputFactory;
    }

    /**
     * Handles the creation of dynamic input structures.
     *
     * @param name Name of given input.
     * @param json Example of json to map.
     * @return InputEntity
     * @throws ServiceException Thrown when ac error occurred on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public InputEntity create(
            String name,
            String json) throws ServiceException {
        try {
            return this.inputRepository.createSync(this.inputFactory.produce(name, json), getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating an input.", e);
        }
    }

    public InputEntity read(UUID id) throws ServiceException {
        try {
            return this.inputRepository.read(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading an input.", e);
        }
    }

    public CompletableFuture<InputEntity> updateAsync(InputEntity entity) throws ServiceException {
        try {
            return this.inputRepository.updateAsync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async updating an input.", e);
        }
    }

    public InputEntity updateSync(InputEntity entity) throws ServiceException {
        try {
            return this.inputRepository.updateSync(entity, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong updating an input.", e);
        }
    }

    public void deleteAsync(UUID id) throws ServiceException {
        try {
            this.inputRepository.deleteAsync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong async deleting an input.", e);
        }
    }

    public void deleteSync(UUID id) throws ServiceException {
        try {
            this.inputRepository.deleteSync(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong deleting an input.", e);
        }
    }

    public Optional<List<InputEntity>> readAll() throws ServiceException {
        try {
            return this.inputRepository.findAll(getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all inputs.", e);
        }
    }

    public Optional<List<InputEntity>> readAllByIds(List<UUID> ids) throws ServiceException {
        try {
            return this.inputRepository.findAllByIds(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong reading all inputs by id.", e);
        }
    }

    public void check(UUID id) throws ServiceException {
        try {
            this.inputRepository.check(id, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking an input.", e);
        }
    }

    public void checkAll(Set<UUID> ids) throws ServiceException {
        try {
            this.inputRepository.checkAll(ids, getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong checking all inputs by ids.", e);
        }
    }
}