package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.action.infrastructure.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeIfEntity;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeIfMapper extends GenericBusinessMapper<NodeTreeIf, NodeTreeIfEntity> {
    @SubclassMapping(source = ComparisonMultiEntity.class, target = ComparisonMulti.class)
    @SubclassMapping(source = ComparisonSingularEntity.class, target = ComparisonSingular.class)
    @SubclassMapping(source = NodeTreeIfEntity.class, target = NodeTreeIf.class)
    @SubclassMapping(source = NodeTreeBlockEntity.class, target = NodeTreeBlock.class)
    @SubclassMapping(source = NodeTreeActionEntity.class, target = NodeTreeAction.class)
    NodeTree<Object> toEntity(NodeTreeEntity entity);

    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    NodeTreeIf toEntity(NodeTreeIfEntity schema);

    @Named("mapNodes")
    default List<NodeTree<?>> mapNodes(List<NodeTreeEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
