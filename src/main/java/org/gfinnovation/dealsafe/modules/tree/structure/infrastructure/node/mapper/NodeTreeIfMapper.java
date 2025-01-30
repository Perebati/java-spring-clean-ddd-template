package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper;

import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeIfEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeIfMapper extends NodeTreeMapper<NodeTreeIf, NodeTreeIfEntity> {
    @Override
    @Mapping(target = "conditionalNodes", source = "conditionalNodes", qualifiedByName = "mapNodes")
    @Mapping(target = "thenNodes", source = "thenNodes", qualifiedByName = "mapNodes")
    @Mapping(target = "elseNodes", source = "elseNodes", qualifiedByName = "mapNodes")
    NodeTreeIf toEntity(NodeTreeIfEntity schema);
}
