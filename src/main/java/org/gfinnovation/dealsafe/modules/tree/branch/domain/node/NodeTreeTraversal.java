package org.gfinnovation.dealsafe.modules.tree.branch.domain.node;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeInterface
 * @since 21/01/2025
 */

public abstract class NodeTreeTraversal<T> extends GenericBusinessEntity {
    public abstract boolean traverse(T inputData);
}
