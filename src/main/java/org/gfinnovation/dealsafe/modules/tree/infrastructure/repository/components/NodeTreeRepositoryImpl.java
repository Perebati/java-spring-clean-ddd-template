package org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.NodeTreeSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.persistence.mapper.NodeTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeRepositoryImpl
 * @since 30/10/2024
 */

@Component
class NodeTreeRepositoryImpl
        extends GenericBusinessRepositoryImpl<NodeTreeEntity, NodeTreeSchema>
        implements NodeTreeRepository {

    @Autowired
    NodeTreeRepositoryImpl(
            NodeTreeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeSchema.class, entityManager), NodeTreeSchema.class);
    }
}