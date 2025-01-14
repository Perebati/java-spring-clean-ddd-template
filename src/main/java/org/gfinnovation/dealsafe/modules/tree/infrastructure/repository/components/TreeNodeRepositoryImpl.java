package org.gfinnovation.dealsafe.modules.tree.infrastructure.repository.components;

import jakarta.persistence.EntityManager;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.GenericBusinessRepositoryImpl;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;
import org.gfinnovation.dealsafe.modules.tree.domain.repository.components.TreeNodeRepository;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.TreeNodeSchema;
import org.gfinnovation.dealsafe.modules.tree.infrastructure.mapper.TreeNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeNodeRepositoryImpl
 * @since 30/10/2024
 */

@Component
class TreeNodeRepositoryImpl
        extends GenericBusinessRepositoryImpl<TreeNode, TreeNodeSchema>
        implements TreeNodeRepository {

    @Autowired
    TreeNodeRepositoryImpl(
            TreeNodeMapper mapper,
            EntityManager entityManager) {
        super(mapper, new SimpleJpaRepository<>(TreeNodeSchema.class, entityManager), TreeNodeSchema.class);
    }
}