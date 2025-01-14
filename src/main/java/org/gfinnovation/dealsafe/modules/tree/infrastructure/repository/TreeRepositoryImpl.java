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
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.TreeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeDynamicRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeNodeRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeStaticRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeStaticRoot;
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

    private final TreeNodeRepository treeNodeRepository;
    private final TreeDynamicRootRepository treeDynamicRootRepository;
    private final TreeRootRepository treeRootRepository;
    private final TreeStaticRootRepository treeStaticRootRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TreeRepositoryImpl(
            TreeNodeRepository treeNodeRepository,
            TreeDynamicRootRepository treeDynamicRootRepository,
            TreeRootRepository treeRootRepository,
            TreeStaticRootRepository treeStaticRootRepository) {
        this.treeNodeRepository = treeNodeRepository;
        this.treeDynamicRootRepository = treeDynamicRootRepository;
        this.treeRootRepository = treeRootRepository;
        this.treeStaticRootRepository = treeStaticRootRepository;
    }

    /**
     * Transactional operation to save a new node on the database.
     *
     * @param newNode   New entity to be saved, mede by factory.
     * @param parent    Parent of given newNode, can be either a root or a node.
     * @param parent_id ParentId of given newNode
     * @return TreeNode
     * @throws RepositoryException Thrown when an error occur on Repository Level
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Transactional
    @Override
    public TreeNode createNode(@NotNull TreeNode newNode, @NotNull Object parent, @NotNull UUID parent_id, @NotNull RepositoryAuth auth) throws RepositoryException {
        try {
            if (parent instanceof TreeStaticRoot) {
                TreeStaticRoot parent_root = this.treeStaticRootRepository.read(parent_id, auth);
                TreeNode createdNodeEntity = this.treeNodeRepository.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.updateGenericRootSync(parent_root, auth);
                return this.treeNodeRepository.read(createdNodeEntity.getId(), auth);
            } else if (parent instanceof TreeDynamicRoot) {
                TreeDynamicRoot parent_root = this.treeDynamicRootRepository.read(parent_id, auth);
                TreeNode createdNodeEntity = this.treeNodeRepository.createSync(newNode, auth);
                parent_root.addNode(createdNodeEntity);
                this.updateGenericRootSync(parent_root, auth);
                return this.treeNodeRepository.read(createdNodeEntity.getId(), auth);
            } else {
                TreeNode parent_node = this.treeNodeRepository.read(parent_id, auth);
                TreeNode createdNodeEntity = this.treeNodeRepository.createSync(newNode, auth);
                this.treeNodeRepository.updateSync(parent_node.addChild(createdNodeEntity), auth);
                return this.treeNodeRepository.read(createdNodeEntity.getId(), auth);
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
            return this.treeStaticRootRepository.read(id, auth);
        } catch (RepositoryEntityNotFoundException e1) {
            try {
                return this.treeDynamicRootRepository.read(id, auth);
            } catch (RepositoryEntityNotFoundException e2) {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RepositoryException("Something went wrong reading a root.", e);
        }
    }

    @Override
    public TreeRoot updateGenericRootSync(@NotNull TreeRoot entity, @NotNull RepositoryAuth auth) throws RepositoryException, IllegalArgumentException {
        try {
            if (entity instanceof TreeDynamicRoot dynamicRoot) {
                return this.treeDynamicRootRepository.updateSync(dynamicRoot, auth);
            } else if (entity instanceof TreeStaticRoot staticRoot) {
                return this.treeStaticRootRepository.updateSync(staticRoot, auth);
            } else {
                throw new IllegalArgumentException("Unexpected TreeRoot type: " + entity.getClass().getName());
            }
        } catch (Exception e) {
            throw new BusinessException("Unexpected error during update of TreeRoot. ERROR_CODE: PATCH-01", e);
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

