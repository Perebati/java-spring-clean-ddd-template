package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeRoot;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeRootRepository;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.TreeRootSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper.TreeRootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeRootRepositoryImpl
 * @since 30/10/2024
 */

@Component
class TreeRootRepositoryImpl
        extends GenericBusinessRepositoryImpl<TreeRoot, TreeRootSchema>
        implements TreeRootRepository {

    @Autowired
    TreeRootRepositoryImpl(
            TreeRootMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(TreeRootSchema.class, entityManager), TreeRootSchema.class);
    }
}