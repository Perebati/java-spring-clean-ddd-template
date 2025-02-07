package org.gfinnovation.dealsafe.modules.input.application.usecase.query;

import org.gfinnovation.dealsafe._shared.modules.application.usecase.NullInputUseCase;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ReadAllInputUseCase
 * @since v1.0 (07/02/2025)
 */

@Component
public class ReadAllInputUseCase extends NullInputUseCase<List<Input>, InputService> {
    public ReadAllInputUseCase(InputService inputService) {
        super(inputService);
    }

    @Override
    public List<Input> execute() {
        return this.service.readAll().get();
    }
}