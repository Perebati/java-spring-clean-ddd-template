package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.repository.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.Node;

public interface ComparisonMultiRepository extends GenericBusinessRepository<ComparisonMulti> {
    ComparisonMulti createMultiComparison(ComparisonMulti newNode, Node<?> parent, RepositoryAuth auth) throws RepositoryException;
}
