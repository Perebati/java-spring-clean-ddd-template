package org.gfinnovation.dealsafe.modules.input.domain.factory;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.input.domain.Input;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.springframework.stereotype.Component;

/**
 * Standard entity factory.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class InputFactoryImpl
 * @since v1.0 (30/11/2024)
 */

@Component
class InputFactoryImpl implements InputFactory {
    public Input produce(@NotNull String name, @NotNull String json) throws SystemGlobalException {
        try {
            return new Input(name, json);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating an input.");
        }
    }
}
