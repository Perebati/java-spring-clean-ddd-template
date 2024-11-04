package org.gfinnovation.dealsafe.domains.operation.entity.factory.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.entity.GenericBusinessFactory;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces.ActionOperationFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handles Action creation.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ActionOperationFactoryImpl
 * @since 30/10/2024
 */

@Component
public class ActionOperationFactoryImpl extends GenericBusinessFactory implements ActionOperationFactory {
    public ActionOperationFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness) {
        super(userBusiness, companyBusiness);
    }

    /**
     * Experimental method for action, will change in the future.
     *
     * @param user_id    UserId
     * @param company_id CompanyID
     * @param url        Message webhook
     * @param message    Message itself
     * @return ActionOperationEntity
     * @throws FactoryException    Thrown when something wrong happened on factory layer
     * @throws ValidationException Thrown when something wrong happened on factory layer
     */
    public ActionOperationEntity produce(UUID user_id, UUID company_id, String url, String message) throws FactoryException, ValidationException {
        try {
            this.validadeBusiness(user_id, company_id);
            return new ActionOperationEntity(user_id, company_id, url, message);
        } catch (Exception e) {
            throw new FactoryException("Something went wrong creating an action operation.", e);
        }
    }
}
