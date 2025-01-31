package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeRepository
 * @since 24/01/2025
 */

@Repository
public interface NodeRepository extends GenericBusinessRepository<Node<Object>> {
}
