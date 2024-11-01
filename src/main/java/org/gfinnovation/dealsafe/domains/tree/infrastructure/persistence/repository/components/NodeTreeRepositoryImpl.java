package org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericRepositoryImpl;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.repository.components.NodeTreeRepository;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.NodeTreeSchema;
import org.gfinnovation.dealsafe.domains.tree.infrastructure.persistence.mapper.NodeTreeMapper;
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
        extends GenericRepositoryImpl<NodeTreeEntity, NodeTreeSchema>
        implements NodeTreeRepository {

    NodeTreeRepositoryImpl(
            NodeTreeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(NodeTreeSchema.class, entityManager));
    }
}