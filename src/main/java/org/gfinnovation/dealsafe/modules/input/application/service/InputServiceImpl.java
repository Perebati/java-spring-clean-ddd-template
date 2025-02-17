package org.gfinnovation.dealsafe.modules.input.application.service;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
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
 * @version v1.0
 * @class InputServiceImpl
 * @since v1.0 (30/11/2024)
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
     * @return InputEntity
     * @throws ApplicationException Thrown when ac error occurred on business level.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */
    public Input createInput(
            CreateInputData request
    ) throws SystemGlobalException {
        try {
            return this.inputRepository.create(
                    this.inputFactory.produce(request.name(), request.json().toString()),
                    getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating an input.", e);
        }
    }
}