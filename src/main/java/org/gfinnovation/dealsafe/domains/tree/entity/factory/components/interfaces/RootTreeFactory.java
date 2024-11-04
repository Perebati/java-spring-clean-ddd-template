package org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRootFactory
 * @since 30/10/2024
 */

public interface RootTreeFactory {
    RootTreeDynamicEntity produce(UUID user_id, UUID company_id, String name, UUID dynamic_input) throws FactoryException, ValidationException;

    RootTreeStaticEntity produce(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) throws FactoryException, ValidationException;
}
