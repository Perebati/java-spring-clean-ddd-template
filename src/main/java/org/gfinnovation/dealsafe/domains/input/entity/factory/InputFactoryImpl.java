package org.gfinnovation.dealsafe.domains.input.entity.factory;

import org.gfinnovation.dealsafe._shared.entity.GenericBusinessFactory;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
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
class InputFactoryImpl extends GenericBusinessFactory implements InputFactory {
    InputFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness) {
        super(userBusiness, companyBusiness);
    }

    public InputEntity produce(UUID user_id, UUID company_id, String name, String json) {
        this.validadeBusiness(user_id, company_id);
        return new InputEntity(user_id, company_id, name, json);
    }
}
