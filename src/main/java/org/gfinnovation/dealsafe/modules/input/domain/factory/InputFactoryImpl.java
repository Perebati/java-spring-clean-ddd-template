package org.gfinnovation.dealsafe.modules.input.domain.factory;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces.InputFactory;
import org.springframework.stereotype.Component;

/**
 * Standard entity factory.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputFactoryImpl
 * @since 30/10/2024
 */

@Component
class InputFactoryImpl implements InputFactory {
    public InputEntity produce(@NotNull String name, @NotNull String json) throws FactoryException {
        try {
            return new InputEntity(name, json);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating an input.", e);
        }
    }
}
