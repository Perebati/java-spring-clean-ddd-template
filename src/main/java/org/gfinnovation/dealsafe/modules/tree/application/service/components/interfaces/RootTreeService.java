package org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeStaticRoot;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeService extends GenericService<TreeRoot> {
    CompletableFuture<TreeStaticRoot> create(String name, PredefinedTypeEnum static_input) throws BusinessException, ValidationException;

    CompletableFuture<TreeDynamicRoot> create(String name, UUID dynamic_input) throws BusinessException, ValidationException;

    Optional<TreeStaticRoot> readRootStatic(UUID id) throws RepositoryException;

    Optional<TreeDynamicRoot> readRootDynamic(UUID id) throws RepositoryException;

    Object readGenericRoot(UUID id) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws RepositoryException;

    TreeRoot updateSync(TreeRoot entity) throws RepositoryException;
}
