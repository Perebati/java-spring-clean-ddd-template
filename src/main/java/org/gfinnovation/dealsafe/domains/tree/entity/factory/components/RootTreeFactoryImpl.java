package org.gfinnovation.dealsafe.domains.tree.entity.factory.components;

import org.gfinnovation.dealsafe._shared.entity.GenericBusinessFactory;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.RootTreeFactory;
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
class RootTreeFactoryImpl extends GenericBusinessFactory implements RootTreeFactory {
    private final InputBusiness inputBusiness;

    public RootTreeFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, InputBusiness inputBusiness) {
        super(userBusiness, companyBusiness);
        this.inputBusiness = inputBusiness;
    }

    public RootTreeDynamicEntity produce(UUID user_id, UUID company_id, String name, UUID dynamic_input) {
        this.validadeBusiness(user_id, company_id);
        this.inputBusiness.check(dynamic_input);
        return new RootTreeDynamicEntity(user_id, company_id, name, dynamic_input);
    }

    public RootTreeStaticEntity produce(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) {
        this.validadeBusiness(user_id, company_id);
        return new RootTreeStaticEntity(user_id, company_id, name, static_input);
    }
}
