package org.gfinnovation.dealsafe.modules.tree.branch.domain;

import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessEntity;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BranchTraversal
 * @since 22/01/2025
 */

public abstract class BranchTraversal extends GenericBusinessEntity {
    public abstract boolean execute(String input);
}