package org.gfinnovation.dealsafe.modules.tree.action.domain;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.DomainException;
import org.springframework.stereotype.Component;

/**
 * Handles Action creation.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationFactoryImpl
 * @since 30/10/2024
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
     * @since 30/10/2024
     */

    public ActionOperation produce(String url, String message) throws SystemGlobalException {
        try {
            return new ActionOperation(url, message);
        } catch (Exception e) {
            throw new DomainException("Something went wrong creating an action operation.");
        }
    }
}
