package org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces;

import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @authorNote n/a
 * @since 30/10/2024
 */
public interface RootTreeBusiness extends GenericBusiness<RootTreeEntity> {
    RootTreeStaticEntity create(UUID user_id, UUID company_id, String name, PredefinedTypeEnum static_input) throws RuntimeException;

    RootTreeDynamicEntity create(UUID user_id, UUID company_id, String name, UUID dynamic_input) throws RuntimeException;

    Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RuntimeException;

    Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RuntimeException;

    Object readGenericRoot(UUID id) throws RuntimeException;

    Optional<UUID> findRootIdByNodeId(UUID node_id);
}
