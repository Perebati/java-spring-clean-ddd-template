package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeIfEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeIfMapper;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
class NodeTreeIfRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeIf, NodeTreeIfEntity>
        implements NodeTreeIfRepository {

    @Autowired
    NodeTreeIfRepositoryImpl(
            NodeTreeIfMapper mapper,
            EntityManager entityManager
    ) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeIfEntity.class, entityManager), NodeTreeIfEntity.class);
    }
}