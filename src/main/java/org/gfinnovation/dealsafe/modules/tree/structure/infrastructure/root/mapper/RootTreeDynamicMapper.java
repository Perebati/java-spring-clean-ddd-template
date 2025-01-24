package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeBlockEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.RootTreeDynamicEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeDynamicMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RootTreeDynamicMapper extends GenericBusinessMapper<RootTreeDynamic, RootTreeDynamicEntity> {
    @SubclassMapping(source = NodeTreeBlockEntity.class, target = NodeTreeBlock.class)
    @SubclassMapping(source = NodeTreeActionEntity.class, target = NodeTreeAction.class)
    NodeTree<Object> toEntity(NodeTreeEntity entity);

    @Override
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "company_id", ignore = true)
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTreeDynamic toEntity(RootTreeDynamicEntity schema);

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
