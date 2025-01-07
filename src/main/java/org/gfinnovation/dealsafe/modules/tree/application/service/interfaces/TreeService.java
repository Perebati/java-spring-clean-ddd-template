package org.gfinnovation.dealsafe.modules.tree.application.service.interfaces;

import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces.RootTreeService;

/**
 * Main business interface of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeBusiness
 * @since 30/10/2024
 */

public interface TreeService {
    NodeTreeService getNodeTreeBusiness();

    RootTreeService getRootTreeBusiness();
}
