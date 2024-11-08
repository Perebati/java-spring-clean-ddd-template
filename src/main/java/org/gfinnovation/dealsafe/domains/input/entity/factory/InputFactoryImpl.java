package org.gfinnovation.dealsafe.domains.input.entity.factory;

import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.entity.factory.interfaces.InputFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
    public InputEntity produce(@NotNull UUID user_id, @NotNull UUID company_id, @NotNull String name, @NotNull String json) throws FactoryException {
        try {
            return new InputEntity(user_id, company_id, name, json);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating an input.", e);
        }
    }
}
