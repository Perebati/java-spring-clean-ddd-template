package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.domain.GenericClass;
import org.gfinnovation.dealsafe._shared.infrastructure.GenericEntity;
import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.action.infrastructure.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonCustomListEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeIfEntity;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeTreeMapper
 * @since v1.0 (06/02/2025)
 */
public interface NodeTreeGenericMapper<E extends GenericClass, S extends GenericEntity>
        extends GenericBusinessMapper<E, S> {
    ComparisonMulti toComparisonMulti(ComparisonMultiEntity entity);

    ComparisonSingular toComparisonSingular(ComparisonSingularEntity entity);

    ComparisonCustomList toComparisonCustomList(ComparisonCustomListEntity entity);

    NodeTreeIf toNodeTreeIf(NodeTreeIfEntity entity);

    NodeTreeBlock toNodeTreeBlock(NodeTreeBlockEntity entity);

    NodeTreeAction toNodeTreeAction(NodeTreeActionEntity entity);

    default NodeTree<NodeInput> toEntity(NodeTreeEntity entity) {
        return switch (entity) {
            case null -> null;
            case ComparisonMultiEntity comparisonMultiEntity -> toComparisonMulti(comparisonMultiEntity);
            case ComparisonSingularEntity comparisonSingularEntity -> toComparisonSingular(comparisonSingularEntity);
            case ComparisonCustomListEntity comparisonCustomListEntity ->
                    toComparisonCustomList(comparisonCustomListEntity);
            case NodeTreeIfEntity nodeTreeIfEntity -> toNodeTreeIf(nodeTreeIfEntity);
            case NodeTreeBlockEntity nodeTreeBlockEntity -> toNodeTreeBlock(nodeTreeBlockEntity);
            case NodeTreeActionEntity nodeTreeActionEntity -> toNodeTreeAction(nodeTreeActionEntity);
            default -> throw new IllegalArgumentException("Unknown node type: " + entity.getClass().getName());
        };
    }

    @Named("mapNodes")
    default List<NodeTree<NodeInput>> mapNodes(List<NodeTreeEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}