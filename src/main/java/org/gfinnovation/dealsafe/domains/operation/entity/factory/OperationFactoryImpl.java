package org.gfinnovation.dealsafe.domains.operation.entity.factory;

import lombok.Getter;
import org.gfinnovation.dealsafe._shared.domains.application.GenericBusinessImpl;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces.ActionOperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces.ComparisonOperationFactory;
import org.gfinnovation.dealsafe.domains.operation.entity.factory.interfaces.OperationFactory;
import org.springframework.stereotype.Component;

/**
 * Handles the creation of operations.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class OperationFactoryImpl
 * @since 30/10/2024
 */

@Component
@Getter
class OperationFactoryImpl extends GenericBusinessImpl implements OperationFactory {
    private final ActionOperationFactory actionOperationFactory;
    private final ComparisonOperationFactory comparisonOperationFactory;

    public OperationFactoryImpl(UserBusiness userBusiness, CompanyBusiness companyBusiness, ActionOperationFactory actionOperationFactory, ComparisonOperationFactory comparisonOperationFactory) {
        super(userBusiness, companyBusiness);
        this.actionOperationFactory = actionOperationFactory;
        this.comparisonOperationFactory = comparisonOperationFactory;
    }
}
