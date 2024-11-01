package org.gfinnovation.dealsafe.domains.tree.entity.factory.interfaces;

import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.NodeTreeFactory;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.RootTreeFactory;


/**
 * Main factory interface
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeFactory
 * @since 30/10/2024
 */
public interface TreeFactory {
    NodeTreeFactory getNodeTreeFactory();

    RootTreeFactory getRootTreeFactory();
}
