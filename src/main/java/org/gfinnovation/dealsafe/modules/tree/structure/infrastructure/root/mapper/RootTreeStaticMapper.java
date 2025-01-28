package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.RootTreeStaticEntity;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeStaticMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface RootTreeStaticMapper extends GenericBusinessMapper<RootTreeStatic, RootTreeStaticEntity> {
    @SubclassMapping(source = NodeTreeBlockEntity.class, target = NodeTreeBlock.class)
    @SubclassMapping(source = NodeTreeActionEntity.class, target = NodeTreeAction.class)
    NodeTree<Object> toEntity(NodeTreeEntity entity);

    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTreeStatic toEntity(RootTreeStaticEntity schema);

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
