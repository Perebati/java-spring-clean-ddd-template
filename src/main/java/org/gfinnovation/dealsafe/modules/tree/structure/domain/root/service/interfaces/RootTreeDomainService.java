package org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTree;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeBusiness
 * @since 30/10/2024
 */

public interface RootTreeDomainService extends GenericService<RootTree<?>> {
    Object readGenericRoot(UUID id) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID node_id) throws RepositoryException;
}
