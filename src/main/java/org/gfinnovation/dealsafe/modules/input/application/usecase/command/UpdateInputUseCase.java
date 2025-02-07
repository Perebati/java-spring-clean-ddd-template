package org.gfinnovation.dealsafe.modules.input.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class UpdateInputUseCase
 * @since v1.0 (07/02/2025)
 */
@Component
public class UpdateInputUseCase extends UseCase<Input, Input, InputService> {
    public UpdateInputUseCase(InputService inputService) {
        super(inputService);
    }

    @Override
    public Input execute(Input input) throws ApplicationException {
        return this.service.update(input);
    }
}
