package org.gfinnovation.dealsafe.modules.tree.branch.domain.root.service.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTreeStatic;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeService extends GenericService<RootTree> {
    RootTreeStatic create(String name, PredefinedTypeEnum static_input) throws ServiceException, ValidationException;

    CompletableFuture<RootTreeDynamic> create(String name, UUID dynamic_input) throws ServiceException, ValidationException;

    Optional<RootTreeStatic> readRootStatic(UUID id) throws RepositoryException;

    Optional<RootTreeDynamic> readRootDynamic(UUID id) throws RepositoryException;

    Object readGenericRoot(UUID id) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws RepositoryException;

    RootTree updateSync(RootTree entity) throws RepositoryException;
}
