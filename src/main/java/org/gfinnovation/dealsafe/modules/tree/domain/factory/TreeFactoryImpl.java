package org.gfinnovation.dealsafe.modules.tree.domain.factory;

import lombok.Getter;
import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces.TreeNodeFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces.TreeRootFactory;
import org.gfinnovation.dealsafe.modules.tree.domain.factory.interfaces.TreeFactory;
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
class TreeFactoryImpl extends GenericServiceImpl implements TreeFactory {
    private final TreeRootFactory treeRootFactory;
    private final TreeNodeFactory treeNodeFactory;

    public TreeFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, TreeRootFactory treeRootFactory, TreeNodeFactory treeNodeFactory) {
        super(userBusiness, companyBusiness);
        this.treeRootFactory = treeRootFactory;
        this.treeNodeFactory = treeNodeFactory;
    }
}
