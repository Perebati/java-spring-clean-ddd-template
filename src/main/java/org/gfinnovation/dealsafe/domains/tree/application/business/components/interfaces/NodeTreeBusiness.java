package org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces;

import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeBusiness extends GenericBusiness<NodeTreeEntity> {
    NodeTreeEntity create(UUID user_id, UUID company_id, String name, Integer sequence, UUID parent_id) throws RuntimeException;
}
