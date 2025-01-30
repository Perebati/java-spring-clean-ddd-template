package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.action.domain.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeEntity;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeBlockMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeBlockMapper extends GenericBusinessMapper<NodeTreeBlock, NodeTreeBlockEntity> {
    @SubclassMapping(source = NodeTreeBlockEntity.class, target = NodeTreeBlock.class)
    @SubclassMapping(source = NodeTreeActionEntity.class, target = NodeTreeAction.class)
    NodeTree<Object> toEntity(NodeTreeEntity entity);

    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    NodeTreeBlock toEntity(NodeTreeBlockEntity schema);

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