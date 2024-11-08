package org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.application.interfaces.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeStaticEntity;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeBusiness extends GenericBusiness<RootTreeEntity> {
    CompletableFuture<RootTreeStaticEntity> create(String name, PredefinedTypeEnum static_input) throws BusinessException, FactoryException, ValidationException, RepositoryException;

    CompletableFuture<RootTreeDynamicEntity> create(String name, UUID dynamic_input) throws BusinessException, FactoryException, ValidationException, RepositoryException;

    Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RepositoryException;

    Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RepositoryException;

    Object readGenericRoot(UUID id) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws RepositoryException;

    RootTreeEntity updateSync(RootTreeEntity entity) throws RepositoryException;
}
