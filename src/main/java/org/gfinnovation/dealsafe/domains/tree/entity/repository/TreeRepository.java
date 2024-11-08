package org.gfinnovation.dealsafe.domains.tree.entity.repository;

import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeEntity;
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

    NodeTreeEntity createNode(UUID user_id, UUID company_id, NodeTreeEntity newNode, Object parent, UUID parent_id) throws RepositoryException;

    Object readGenericRoot(UUID user_id, UUID company_id, UUID id) throws RepositoryException, EntityNotFoundException;

    RootTreeEntity updateGenericRootSync(UUID user_id, UUID company_id, RootTreeEntity entity) throws RepositoryException;

    Optional<UUID> findRootIdByNodeId(UUID nodeId);
}