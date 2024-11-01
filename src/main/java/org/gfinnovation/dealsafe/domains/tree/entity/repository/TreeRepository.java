package org.gfinnovation.dealsafe.domains.tree.entity.repository;

import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeStaticRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Main repository interface of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface TreeRepository
 * @since 30/10/2024
 */
public interface TreeRepository {

    NodeTreeRepository getNodeTreeRepository();

    RootTreeDynamicRepository getRootTreeDynamicRepository();

    RootTreeRepository getRootTreeRepository();

    RootTreeStaticRepository getRootTreeStaticRepository();

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}