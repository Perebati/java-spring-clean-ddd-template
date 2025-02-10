package org.gfinnovation.dealsafe.modules.input.application.usecase.command;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.adapter.web.request.CreateInputData;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CreateInputUseCase
 * @since v1.0 (07/02/2025)
 */
@Component
public class CreateInputUseCase extends UseCase<CreateInputData, Input, InputService> {
    public CreateInputUseCase(InputService inputService) {
        super(inputService);
    }

    @Override
    public Input execute(CreateInputData input) throws ApplicationException {
        return this.service.createInput(input);
    }
}