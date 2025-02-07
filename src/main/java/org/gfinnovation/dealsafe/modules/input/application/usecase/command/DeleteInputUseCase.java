package org.gfinnovation.dealsafe.modules.input.application.usecase.command;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullOutputUseCase;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class DeleteInputUseCase
 * @since v1.0 (07/02/2025)
 */
@Component
public class DeleteInputUseCase extends NullOutputUseCase<UUID, InputService> {
    protected DeleteInputUseCase(InputService inputService) {
        super(inputService);
    }

    @Override
    public void execute(UUID input) {
        this.service.delete(input);
    }
}
