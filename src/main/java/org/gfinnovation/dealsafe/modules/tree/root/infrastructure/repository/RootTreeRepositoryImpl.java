package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryEntityNotFoundException;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RootTreeRepositoryImpl
 * @since 30/10/2024
 */

@Repository
class RootTreeRepositoryImpl
        implements RootTreeRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    RootTreeRepositoryImpl(
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository
    ) {
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
    }

    /**
     * @param id Identification of given root.
     * @return Object
     * @throws RepositoryException               Thrown when an error occur on Repository Level.
     * @throws RepositoryEntityNotFoundException Thrown when an error occur on Repository Level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            return this.rootTreeStaticRepository.read(id, auth);
        } catch (RepositoryEntityNotFoundException e1) {
            try {
                return this.rootTreeDynamicRepository.read(id, auth);
            } catch (RepositoryEntityNotFoundException e2) {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong reading a root.", e);
        }
    }

    /**
     * The goal of this method is to find the root node
     * that originate the tree where the given node(id) exists.
     *
     * @param node_id NodeId.
     * @return Optional of UUID
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) {
        String sql = """
                WITH RECURSIVE hierarchy AS (
                    SELECT
                        n.id AS node_id,
                        n.parent_id,
                        n.parent_type
                    FROM
                        tree_root_node n
                    WHERE
                        n.id = :nodeId
                    UNION ALL
                    SELECT
                        n2.id AS node_id,
                        n2.parent_id,
                        n2.parent_type
                    FROM
                        hierarchy h
                    JOIN
                        tree_root_node n2 ON n2.id = h.parent_id
                    WHERE
                        h.parent_type = 'NODE' AND h.parent_id IS NOT NULL
                )
                SELECT
                    h.parent_id AS root_id
                FROM
                    hierarchy h
                WHERE
                    h.parent_type = 'ROOT'
                LIMIT 1
                """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("nodeId", node_id);

        try {
            Object result = query.getSingleResult();
            UUID rootId = null;

            if (result != null) {
                if (result instanceof UUID) {
                    rootId = (UUID) result;
                } else if (result instanceof String) {
                    rootId = UUID.fromString((String) result);
                } else if (result instanceof byte[]) {
                    ByteBuffer bb = ByteBuffer.wrap((byte[]) result);
                    long high = bb.getLong();
                    long low = bb.getLong();
                    rootId = new UUID(high, low);
                } else {
                    throw new IllegalArgumentException("Tipo de resultado inesperado: " + result.getClass());
                }
            }

            return Optional.ofNullable(rootId);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}