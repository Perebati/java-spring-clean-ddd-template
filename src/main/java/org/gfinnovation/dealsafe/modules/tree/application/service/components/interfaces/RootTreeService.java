package org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStaticEntity;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeService extends GenericService<RootTreeEntity> {
    CompletableFuture<RootTreeStaticEntity> create(String name, PredefinedTypeEnum static_input) throws BusinessException, ValidationException;

    CompletableFuture<RootTreeDynamicEntity> create(String name, UUID dynamic_input) throws BusinessException, ValidationException;

    Optional<RootTreeStaticEntity> readRootStatic(UUID id) throws RepositoryException;

    Optional<RootTreeDynamicEntity> readRootDynamic(UUID id) throws RepositoryException;

    Object readGenericRoot(UUID id) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws RepositoryException;

    RootTreeEntity updateSync(RootTreeEntity entity) throws RepositoryException;
}
