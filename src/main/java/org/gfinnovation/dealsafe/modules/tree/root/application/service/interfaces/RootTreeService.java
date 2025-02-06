package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeService
 * @since v1.0 (30/11/2024)
 */
public interface RootTreeService {
    Object readGenericRoot(UUID id) throws SystemGlobalException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws SystemGlobalException;
}
