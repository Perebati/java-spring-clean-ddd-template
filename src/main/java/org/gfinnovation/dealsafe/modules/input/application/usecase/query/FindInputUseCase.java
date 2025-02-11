package org.gfinnovation.dealsafe.modules.input.application.usecase.query;

import org.gfinnovation.dealsafe._shared.application.usecase.UseCase;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ReadInputUseCase
 * @since v1.0 (07/02/2025)
 */
@Component
public class FindInputUseCase extends UseCase<UUID, Optional<Input>, InputService>{
    public FindInputUseCase(InputService inputService) {
        super(inputService);
    }

    @Override
    public Optional<Input> execute(UUID input) throws ApplicationException {
        return this.service.findById(input);
    }
}
