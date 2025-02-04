package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeBlockMapper;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBlockRepositoryImpl
 * @since 30/10/2024
 */

@Repository
class NodeTreeBlockRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeBlock, NodeTreeBlockEntity>
        implements NodeTreeBlockRepository {

    @Autowired
    NodeTreeBlockRepositoryImpl(
            NodeTreeBlockMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeBlockEntity.class, entityManager), NodeTreeBlockEntity.class);
    }
}