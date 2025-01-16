package org.gfinnovation.dealsafe.modules.tree.node.domain.factory;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.node.domain.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.node.domain.aggregates.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.node.domain.aggregates.RootTreeStatic;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handles the creation and validation of Root nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeRootFactoryImpl
 * @since 30/10/2024
 */

@Component
class RootTreeFactoryImpl implements RootTreeFactory {
    private final InputService inputService;

    RootTreeFactoryImpl(InputService inputService) {
        this.inputService = inputService;
    }

    /**
     * Handles the creation of a root that has a reference to a dynamic input entity id.
     *
     * @param name          Name of the given root.
     * @param dynamic_input id of the dynamic input entity.
     * @return RootTreeDynamic
     * @throws FactoryException    Thrown when an error on factory level occurs.
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public RootTreeDynamic produce(String name, UUID dynamic_input) throws FactoryException {
        try {
            this.inputService.check(dynamic_input);
            return new RootTreeDynamic(name, dynamic_input);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a dynamic root node.", e);
        }
    }

    /**
     * Handles the creation of a root that has a reference to a predefined input entity.
     *
     * @param name         Name of the given root.
     * @param static_input Type of the predefined input entity.
     * @return RootTreeStatic
     * @throws FactoryException    Thrown when an error on factory level occurs.
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public RootTreeStatic produce(String name, PredefinedTypeEnum static_input) throws FactoryException {
        try {
            return new RootTreeStatic(name, static_input);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a predefined root node.", e);
        }
    }
}
