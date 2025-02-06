package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeMapper;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeRepositoryImpl
 * @since v1.0 (06/02/2025)
 */
@Repository
class NodeRepositoryImpl
        extends GenericBusinessRepositoryImpl<Node<Object>, NodeEntity>
        implements NodeRepository {

    @Autowired
    NodeRepositoryImpl(
            NodeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(NodeEntity.class, entityManager), NodeEntity.class);
    }
}