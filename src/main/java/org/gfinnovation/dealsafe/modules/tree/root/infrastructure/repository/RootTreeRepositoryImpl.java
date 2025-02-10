package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.constraints.NotNull;
import org.gfinnovation.dealsafe._shared.infrastructure.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.InfrastructureException;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper.RootTreeMapper;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeRepositoryImpl
 * @since v1.0 (30/11/2024)
 */
@Repository
class RootTreeRepositoryImpl
        extends GenericBusinessRepositoryImpl<RootTree<?>, RootTreeEntity>
        implements RootTreeRepository {
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    RootTreeRepositoryImpl(
            RootTreeMapper mapper,
            EntityManager entityManager,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeStaticRepository rootTreeStaticRepository
    ) {
        super(mapper, new SimpleJpaRepository<>(RootTreeEntity.class, entityManager), RootTreeEntity.class);
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
    }

    /**
     * @param id Identification of given root.
     * @return Object
     * @throws InfrastructureException Thrown when an error occur on Repository Level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Object readGenericRoot(@NotNull UUID id, @NotNull RepositoryAuth auth) throws SystemGlobalException {
        try {
            RootTree<?> rootTree = this.read(id, auth);
            if (rootTree.getNodeType().equals(Node.NodeType.ROOT_DYNAMIC)) {
                return this.rootTreeDynamicRepository.read(id, auth);
            } else {
                return this.rootTreeStaticRepository.read(id, auth);
            }
        } catch (Exception e) {
            throw new InfrastructureException("Something went wrong reading a root.");
        }
    }

    /**
     * The goal of this method is to find the root node
     * that originate the tree where the given node(id) exists.
     *
     * @param node_id NodeId.
     * @return Optional of UUID
     * @author Lucas Batista Pereira
     * @since v1.0 (30/11/2024)
     */
    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) throws SystemGlobalException {
        try {
            String sql = """
                    
                        WITH RECURSIVE hierarchy AS (
                        SELECT
                            n.id AS node_id,
                            n.parent_id,
                            n.parent_type
                        FROM
                            tree_node n
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
                            tree_node n2 ON n2.id = h.parent_id
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
                    switch (result) {
                        case UUID uuid -> rootId = uuid;
                        case String s -> rootId = UUID.fromString(s);
                        case byte[] bytes -> {
                            ByteBuffer bb = ByteBuffer.wrap(bytes);
                            long high = bb.getLong();
                            long low = bb.getLong();
                            rootId = new UUID(high, low);
                        }
                        default -> throw new IllegalArgumentException("Unexpected node type: " + result.getClass());
                    }
                }

                return Optional.ofNullable(rootId);
            } catch (NoResultException e) {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new InfrastructureException("An error occurred when searching for a root id.");
        }
    }
}