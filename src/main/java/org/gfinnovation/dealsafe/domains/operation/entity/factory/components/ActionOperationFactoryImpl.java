package org.gfinnovation.dealsafe.domains.operation.entity.factory.components;

import org.gfinnovation.dealsafe._shared.entity.GenericBusinessFactory;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
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

    public ActionOperationEntity produce(UUID user_id, UUID company_id, String url, String message) {
        this.validadeBusiness(user_id, company_id);
        return new ActionOperationEntity(user_id, company_id, url, message);
    }
}
