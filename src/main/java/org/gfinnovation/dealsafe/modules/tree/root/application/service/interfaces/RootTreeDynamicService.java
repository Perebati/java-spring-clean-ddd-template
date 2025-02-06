package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class RootTreeDynamicService
 * @since v1.0 (06/02/2025)
 */
public interface RootTreeDynamicService extends GenericService<RootTreeDynamic> {
    RootTreeDynamic create(
            String name,
            UUID dynamic_input
    ) throws SystemGlobalException;
}
