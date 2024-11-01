package org.gfinnovation.dealsafe.domains.tree.entity.factory.components;

import org.gfinnovation.dealsafe._shared.entity.GenericBusinessFactory;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.NodeTreeFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handles the creation and validation of Nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeFactoryImpl
 * @since 30/10/2024
 */
@Component
class NodeTreeFactoryImpl extends GenericBusinessFactory implements NodeTreeFactory {
    public NodeTreeFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness) {
        super(userBusiness, companyBusiness);
    }

    public NodeTreeEntity produce(UUID user_id, UUID company_id, String name, Integer sequence) {
        this.validadeBusiness(user_id, company_id);
        return new NodeTreeEntity(user_id, company_id, name, sequence);
    }
}
