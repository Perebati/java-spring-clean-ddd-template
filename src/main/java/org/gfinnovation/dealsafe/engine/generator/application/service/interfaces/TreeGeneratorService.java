package org.gfinnovation.dealsafe.engine.generator.application.service.interfaces;

import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class TreeGeneratorService
 * @since v1.0 (13/02/2025)
 */

public interface TreeGeneratorService {
    RootTree<?> createTree(RootTree<?> root);
}
