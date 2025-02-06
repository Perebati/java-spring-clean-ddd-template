package org.gfinnovation.dealsafe.modules.tree.action.domain;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.springframework.stereotype.Component;

/**
 * Handles Action creation.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionOperationFactoryImpl
 * @since v1.0 (30/11/2024)
 */

@Component
public class ActionOperationFactoryImpl implements ActionOperationFactory {


    /**
     * Experimental method for action, will change in the future.
     *
     * @param url     Message webhook.
     * @param message Message itself.
     * @return ActionOperation
     * @throws DomainException     Thrown when something wrong happened on factory layer.
     * @throws ValidationException Thrown when something wrong happened on factory layer.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */

    public ActionOperation produce(String url, String message) throws SystemGlobalException {
        try {
            return new ActionOperation(url, message);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating an action operation.");
        }
    }
}
