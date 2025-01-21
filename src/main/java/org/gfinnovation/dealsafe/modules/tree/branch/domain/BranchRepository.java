package org.gfinnovation.dealsafe.modules.tree.branch.domain;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface BranchRepository
 * @since 20/01/2025
 */

@Repository
public interface BranchRepository extends GenericBusinessRepository<Branch> {
}