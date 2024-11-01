package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.TreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.RootTreeStaticRepository;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

/**
 * Main repository implementation of this domain.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeRepositoryImpl
 * @since 30/10/2024
 */
@Getter
@Repository
class TreeRepositoryImpl implements TreeRepository {

    private final NodeTreeRepository nodeTreeRepository;
    private final RootTreeDynamicRepository rootTreeDynamicRepository;
    private final RootTreeRepository rootTreeRepository;
    private final RootTreeStaticRepository rootTreeStaticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    TreeRepositoryImpl(NodeTreeRepository nodeTreeRepository, RootTreeDynamicRepository rootTreeDynamicRepository, RootTreeRepository rootTreeRepository, RootTreeStaticRepository rootTreeStaticRepository) {
        this.nodeTreeRepository = nodeTreeRepository;
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeRepository = rootTreeRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
    }

    /**
     * The goal of this method is to find the root node
     * that originate the tree where the given node(id) exists.
     *
     * @author Lucas Batista Pereira
     * @param node_id NodeId.
     * @return Optional of UUID
     * @since 30/10/2024
     */
    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) {
        String sql = """
                WITH RECURSIVE hierarchy AS (
                    -- Caso base: seleciona o nó inicial com o node_id fornecido
                    SELECT
                        n.id AS node_id,
                        n.parent_id,
                        n.parent_type
                    FROM
                        tree_root_node n
                    WHERE
                        n.id = :nodeId

                    UNION ALL

                    -- Passo recursivo: percorre os nós pais enquanto o parent_type for 'NODE'
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
                -- Seleciona o parent_id quando o parent_type for 'ROOT', que é o UUID do nó raiz
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
            // Executa a consulta e obtém o resultado
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

