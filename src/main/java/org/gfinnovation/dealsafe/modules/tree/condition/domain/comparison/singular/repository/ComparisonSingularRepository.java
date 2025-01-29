package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.repository;

import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.singular.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonSingularRepository extends GenericBusinessRepository<ComparisonSingular> {
    ComparisonSingular createComparison(ComparisonSingular newOperation, NodeTree<?> parent, RepositoryAuth auth) throws RepositoryException;
}
