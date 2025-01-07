package org.gfinnovation.dealsafe.modules.tree.domain.factory.components.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStaticEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRootFactory
 * @since 30/10/2024
 */

public interface RootTreeFactory {
    RootTreeDynamicEntity produce(UUID user_id, UUID company_id, String name, UUID dynamic_input) throws FactoryException;

    RootTreeStaticEntity produce(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) throws FactoryException;
}
