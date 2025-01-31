package org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces;

import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRootFactory
 * @since 30/10/2024
 */

public interface RootTreeFactory {
    RootTreeDynamic produce(String name, UUID dynamic_input) throws FactoryException;

    RootTreeStatic produce(String name, PredefinedTypeEnum static_input) throws FactoryException;
}
