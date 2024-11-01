package org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces;

import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeFactory
 * @since 30/10/2024
 */

public interface NodeTreeFactory {
    NodeTreeEntity produce(UUID user_id, UUID company_id, String name, Integer sequence);
}
