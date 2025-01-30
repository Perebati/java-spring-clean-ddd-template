package org.gfinnovation.dealsafe.modules.tree.comparison.domain.repository;

import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ComparisonOperationRepository
 * @since 30/10/2024
 */
public interface ComparisonSingularRepository extends GenericBusinessRepository<ComparisonSingular> {
    ComparisonSingular createSingComparison(ComparisonSingular newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException;

}
