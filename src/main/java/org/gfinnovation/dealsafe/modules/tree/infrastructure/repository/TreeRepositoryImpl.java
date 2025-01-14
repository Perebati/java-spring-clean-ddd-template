package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryEntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.configuration.security.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.RootTreeStatic;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TreeRepositoryImpl(
            NodeTreeRepository nodeTreeRepository,
            RootTreeDynamicRepository rootTreeDynamicRepository,
            RootTreeRepository rootTreeRepository,
            RootTreeStaticRepository rootTreeStaticRepository) {
        this.nodeTreeRepository = nodeTreeRepository;
        this.rootTreeDynamicRepository = rootTreeDynamicRepository;
        this.rootTreeRepository = rootTreeRepository;
        this.rootTreeStaticRepository = rootTreeStaticRepository;
    }

    /**
     * Transactional operation to save a new node on the database.
     *
     * @param newNode   New entity to be saved, mede by factory.
     * @param parent    Parent of given newNode, can be either a root or a node.
     * @param parent_id ParentId of given newNode
     * @return NodeTree
     * @throws RepositoryException Thrown when an error occur on Repository Level
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Transactional
    @Override
    public NodeTree createNode(@NotNull NodeTree newNode, @NotNull Object parent, @NotNull UUID parent_id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            if (parent instanceof RootTreeStatic) {
                RootTreeStatic parent_root = this.rootTreeStaticRepository.read(parent_id, auth);
                NodeTree createdNodeEntity = this.nodeTreeRepository.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.updateGenericRootSync(parent_root, auth);
                return this.nodeTreeRepository.read(createdNodeEntity.getId(), auth);
            } else if (parent instanceof RootTreeDynamic) {
                RootTreeDynamic parent_root = this.rootTreeDynamicRepository.read(parent_id, auth);
                NodeTree createdNodeEntity = this.nodeTreeRepository.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.updateGenericRootSync(parent_root, auth);
                return this.nodeTreeRepository.read(createdNodeEntity.getId(), auth);
            } else {
                NodeTree parent_node = this.nodeTreeRepository.read(parent_id, auth);
                NodeTree createdNodeEntity = this.nodeTreeRepository.createSync(newNode, auth);
                this.nodeTreeRepository.updateSync(parent_node.addChild(createdNodeEntity), auth);
                return this.nodeTreeRepository.read(createdNodeEntity.getId(), auth);
            }
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong creating a new node.", e);
        }
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

    @Override
    public RootTree updateGenericRootSync(@NotNull RootTree entity, @NotNull RepositoryAuth auth) throws RepositoryException, IllegalArgumentException {
        try {
            if (entity instanceof RootTreeDynamic dynamicRoot) {
                return this.rootTreeDynamicRepository.updateSync(dynamicRoot, auth);
            } else if (entity instanceof RootTreeStatic staticRoot) {
                return this.rootTreeStaticRepository.updateSync(staticRoot, auth);
            } else {
                throw new IllegalArgumentException("Unexpected RootTree type: " + entity.getClass().getName());
            }
        } catch (Exception e) {
            throw new BusinessException("Unexpected error during update of RootTree. ERROR_CODE: PATCH-01", e);
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

