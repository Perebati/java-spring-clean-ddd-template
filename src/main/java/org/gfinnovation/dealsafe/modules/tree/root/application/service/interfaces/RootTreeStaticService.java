package org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class RootTreeStaticService
 * @since v1.0 (06/02/2025)
 */
public interface RootTreeStaticService extends GenericService<RootTreeStatic> {
    RootTreeStatic create(
            String name,
            PredefinedTypeEnum static_input
    ) throws SystemGlobalException;
}
