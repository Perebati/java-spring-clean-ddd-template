package org.gfinnovation.dealsafe.domains.tree.application.business.interfaces;

import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.NodeTreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces.RootTreeBusiness;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeBusiness
 * @authorNote Main business interface of this domain.
 * @since 30/10/2024
 */
public interface TreeBusiness {
    NodeTreeBusiness getNodeTreeBusiness();

    RootTreeBusiness getRootTreeBusiness();
}
