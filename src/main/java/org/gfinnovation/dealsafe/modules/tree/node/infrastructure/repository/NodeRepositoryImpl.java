package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeMapper;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeRepositoryImpl
 * @since 24/01/2025
 */

@Component
public class NodeRepositoryImpl
        extends GenericBusinessRepositoryImpl<Node<Object>, NodeEntity>
        implements NodeRepository {

    @Autowired
    NodeRepositoryImpl(
            NodeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(NodeEntity.class, entityManager), NodeEntity.class);
    }
}
