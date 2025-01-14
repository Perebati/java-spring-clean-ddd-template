package org.gfinnovation.dealsafe.modules.tree.domain.factory.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces.TreeRootFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeStaticRoot;
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
class TreeRootFactoryImpl implements TreeRootFactory {
    private final InputService inputService;

    TreeRootFactoryImpl(InputService inputService) {
        this.inputService = inputService;
    }

    /**
     * Handles the creation of a root that has a reference to a dynamic input entity id.
     *
     * @param name          Name of the given root.
     * @param dynamic_input id of the dynamic input entity.
     * @return TreeDynamicRoot
     * @throws FactoryException    Thrown when an error on factory level occurs.
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public TreeDynamicRoot produce(String name, UUID dynamic_input) throws FactoryException {
        try {
            this.inputService.check(dynamic_input);
            return new TreeDynamicRoot(name, dynamic_input);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a dynamic root node.", e);
        }
    }

    /**
     * Handles the creation of a root that has a reference to a predefined input entity.
     *
     * @param name         Name of the given root.
     * @param static_input Type of the predefined input entity.
     * @return TreeStaticRoot
     * @throws FactoryException    Thrown when an error on factory level occurs.
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public TreeStaticRoot produce(String name, PredefinedTypeEnum static_input) throws FactoryException {
        try {
            return new TreeStaticRoot(name, static_input);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a predefined root node.", e);
        }
    }
}
