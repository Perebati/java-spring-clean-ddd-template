package org.gfinnovation.dealsafe.domains.tree.entity.factory;

import lombok.Getter;
import org.gfinnovation.dealsafe._shared.domains.application.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces.TreeFactory;
import org.springframework.stereotype.Component;

/**
 * Main factory implementation.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeFactoryImpl
 * @since 30/10/2024
 */

@Getter
@Component
class TreeFactoryImpl extends GenericBusinessImpl implements TreeFactory {
    private final RootTreeFactory rootTreeFactory;
    private final NodeTreeFactory nodeTreeFactory;

    public TreeFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, RootTreeFactory rootTreeFactory, NodeTreeFactory nodeTreeFactory) {
        super(userBusiness, companyBusiness);
        this.rootTreeFactory = rootTreeFactory;
        this.nodeTreeFactory = nodeTreeFactory;
    }
}
