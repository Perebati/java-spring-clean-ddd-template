package org.gfinnovation.dealsafe.modules.input.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.modules.input.infrastructure.repository.interfaces.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
class InputServiceImpl
        extends GenericServiceImpl<Input, InputRepository>
        implements InputService {
    private final InputFactory inputFactory;
    private final InputRepository inputRepository;

    @Autowired
    public InputServiceImpl(
            InputFactory inputFactory,
            InputRepository inputRepository
    ) {
        super(inputRepository);
        this.inputFactory = inputFactory;
        this.inputRepository = inputRepository;
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
    public Input create(
            String name,
            String json
    ) throws ServiceException {
        try {
            return this.inputRepository.createSync(
                    this.inputFactory.produce(name, json),
                    getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating an input.", e);
        }
    }
}