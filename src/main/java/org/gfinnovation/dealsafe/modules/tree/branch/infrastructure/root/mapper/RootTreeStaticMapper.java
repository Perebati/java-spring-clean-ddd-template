package org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.root.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.branch.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.NodeTreeActionEntity;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.node.NodeTreeEntity;
import org.gfinnovation.dealsafe.modules.tree.branch.infrastructure.root.RootTreeStaticEntity;
import org.mapstruct.*;

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
public interface RootTreeStaticMapper extends GenericBusinessMapper<RootTreeStatic, RootTreeStaticEntity> {
    @SubclassMapping(source = NodeTreeActionEntity.class, target = NodeTreeAction.class)
    @SubclassMapping(source = NodeTreeEntity.class,       target = NodeTree.class)
    NodeTree toEntity(NodeTreeEntity entity);
    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTreeStatic toEntity(RootTreeStaticEntity schema);

    @Named("mapNodes")
    default List<NodeTree> mapNodes(List<NodeTreeEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
