package org.gfinnovation.dealsafe.modules.tree.application.service.components.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeService extends GenericService<TreeNode> {
    TreeNode create(String name, Integer sequence, UUID parent_id) throws BusinessException;
}
