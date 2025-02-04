package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.factory.interfaces;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;

public interface NodeTreeRepositoryFactory {
    <T extends Node<?>> GenericBusinessRepository<T> getRepositoryForNode(T node);
}
