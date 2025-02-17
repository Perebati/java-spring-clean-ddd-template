package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Handles action operations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionOperationImpl
 * @since v1.0 (30/11/2024)
 */
@Service
public class ActionOperationServiceImpl
        extends GenericServiceImpl<ActionOperation, ActionOperationRepository>
        implements ActionOperationService {
    @Autowired
    public ActionOperationServiceImpl(
            ActionOperationRepository actionOperationRepository
    ) {
        super(actionOperationRepository);
    }

    /**
     * Creates an action linked to a comparison operation.
     *
     * @param user_id      Userid.
     * @param company_id   CompanyId.
     * @param url          Target message url.
     * @param message      Message given.
     * @param operation_id Parent operation.
     * @return ActionOperation
     * @throws ApplicationException When an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */

    @Override
    public ActionOperation create(UUID user_id,
                                  UUID company_id,
                                  String url,
                                  String message,
                                  UUID operation_id)
            throws SystemGlobalException {
        try {
//            Comparison operation = this.comparisonOperationBusiness.read(operation_id);
//            ActionOperation newOperationAction = this.actionOperationFactory.produce(url, message);
//            operation.addAction(newOperationAction);
//            this.comparisonOperationBusiness.updateSync(operation);
//            return newOperationAction;
            return null;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong checking an action operation.", e);
        }
    }
}
