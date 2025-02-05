package org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRootFactory
 * @since 30/10/2024
 */

public interface RootTreeFactory {
    RootTreeDynamic produce(String name, UUID dynamic_input) throws SystemGlobalException;

    RootTreeStatic produce(String name, PredefinedTypeEnum static_input) throws SystemGlobalException;
}
