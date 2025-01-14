package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeStaticRootRepository;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeStaticRoot;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.TreeStaticRootSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper.TreeStaticRootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeStaticRootRepositoryImpl
 * @since 30/10/2024
 */

@Component
class TreeStaticRootRepositoryImpl
        extends GenericBusinessRepositoryImpl<TreeStaticRoot, TreeStaticRootSchema>
        implements TreeStaticRootRepository {

    @Autowired
    TreeStaticRootRepositoryImpl(
            TreeStaticRootMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(TreeStaticRootSchema.class, entityManager), TreeStaticRootSchema.class);
    }
}
