package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.factory.interfaces;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeTreeRepositoryFactory
 * @since v1.0 (06/02/2025)
 */
public interface NodeTreeRepositoryFactory {
    <T extends Node<?>> GenericBusinessRepository<T> getRepositoryForNode(T node);
}
