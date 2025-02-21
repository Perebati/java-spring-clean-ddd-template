package org.gfinnovation.dealsafe.modules.tree.generator.application.service.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeGeneratorService
 * @since v1.0 (13/02/2025)
 */

public interface TreeGeneratorService {
    RootTree<?> createTree(RootTree<?> root) throws SystemGlobalException;
    RootTree<?> updateTree(UUID id, RootTree<?> root) throws SystemGlobalException;
    RootTree<?> reverseTree(UUID treeId, UUID historyId) throws SystemGlobalException;
}
