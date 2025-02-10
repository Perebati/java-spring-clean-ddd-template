package org.gfinnovation.dealsafe.modules.tree.root.domain.factory;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces.RootTreeFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handles the creation and validation of Root nodes.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeRootFactoryImpl
 * @since v1.0 (30/11/2024)
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
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */
    public RootTreeDynamic produce(String name, UUID dynamic_input) throws SystemGlobalException {
        try {
            this.inputService.check(dynamic_input);
            return new RootTreeDynamic(name, dynamic_input);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a dynamic root node.");
        }
    }

    /**
     * Handles the creation of a root that has a reference to a predefined input entity.
     *
     * @param name         Name of the given root.
     * @param static_input Type of the predefined input entity.
     * @return RootTreeStatic
     * @throws DomainException     Thrown when an error on factory level occurs.
     * @throws ValidationException Thrown when an error on factory level occurs.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */

    public RootTreeStatic produce(String name, PredefinedTypeEnum static_input) throws SystemGlobalException {
        try {
            return new RootTreeStatic(name, static_input);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating a predefined root node.");
        }
    }
}
