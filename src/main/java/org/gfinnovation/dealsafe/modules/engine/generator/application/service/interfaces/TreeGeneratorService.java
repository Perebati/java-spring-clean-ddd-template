package org.gfinnovation.dealsafe.modules.engine.generator.application.service.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeGeneratorService
 * @since v1.0 (13/02/2025)
 */

public interface TreeGeneratorService {
    RootTree<?> createTree(RootTree<?> root) throws SystemGlobalException;
}
