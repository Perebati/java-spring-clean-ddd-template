package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.factory;

import lombok.AllArgsConstructor;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeActionRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonMultiRepository;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.repository.interfaces.ComparisonSingularRepository;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.factory.interfaces.NodeTreeRepositoryFactory;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeBlockRepository;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.repository.interfaces.NodeTreeIfRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class NodeTreeRepositoryFactoryImpl implements NodeTreeRepositoryFactory {
    private final NodeTreeBlockRepository nodeTreeBlockRepository;
    private final NodeTreeIfRepository nodeTreeIfRepository;
    private final NodeTreeActionRepository nodeTreeActionRepository;
    private final ComparisonMultiRepository comparisonMultiRepository;
    private final ComparisonSingularRepository comparisonSingularRepository;

    @SuppressWarnings("unchecked")
    public <T extends Node<?>> GenericBusinessRepository<T> getRepositoryForNode(T node) {
        return switch (node.getNodeType()) {
            case Node.NodeType.NODE_BLOCK -> (GenericBusinessRepository<T>) nodeTreeBlockRepository;
            case Node.NodeType.NODE_IF -> (GenericBusinessRepository<T>) nodeTreeIfRepository;
            case Node.NodeType.NODE_ACTION -> (GenericBusinessRepository<T>) nodeTreeActionRepository;
            case Node.NodeType.CONDITIONAL_COMPARISON_SINGULAR ->
                    (GenericBusinessRepository<T>) comparisonSingularRepository;
            case Node.NodeType.CONDITIONAL_COMPARISON_MULTIPLE ->
                    (GenericBusinessRepository<T>) comparisonMultiRepository;
            default -> throw new IllegalArgumentException("NodeType não suportado: " + node.getNodeType());
        };
    }
}