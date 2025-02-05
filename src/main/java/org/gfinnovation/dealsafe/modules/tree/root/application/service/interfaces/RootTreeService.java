package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeService {
    Object readGenericRoot(UUID id) throws SystemGlobalException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws SystemGlobalException;
}
