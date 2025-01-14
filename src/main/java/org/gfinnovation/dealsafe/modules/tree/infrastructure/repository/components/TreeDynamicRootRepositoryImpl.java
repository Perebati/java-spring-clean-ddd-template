package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeDynamicRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.TreeDynamicRootSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper.TreeDynamicRootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeDynamicRootRepositoryImpl
 * @since 30/10/2024
 */

@Component
class TreeDynamicRootRepositoryImpl
        extends GenericBusinessRepositoryImpl<TreeDynamicRoot, TreeDynamicRootSchema>
        implements TreeDynamicRootRepository {

    @Autowired
    TreeDynamicRootRepositoryImpl(
            TreeDynamicRootMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(TreeDynamicRootSchema.class, entityManager), TreeDynamicRootSchema.class);
    }
}