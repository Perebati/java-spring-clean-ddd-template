package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.mapper.NodeMapper;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeRepository;
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
        extends GenericBusinessRepositoryImpl<Node<NodeInput>, NodeEntity>
        implements NodeRepository {

    @Autowired
    NodeRepositoryImpl(
            NodeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(NodeEntity.class, entityManager), NodeEntity.class);
    }
}