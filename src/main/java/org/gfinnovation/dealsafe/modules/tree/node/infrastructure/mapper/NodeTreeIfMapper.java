package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.mapper.NodeTreeGenericMapper;
import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeIf;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeIfEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class NodeTreeIfMapper
 * @since v1.0 (06/02/2025)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeIfMapper extends NodeTreeGenericMapper<NodeTreeIf, NodeTreeIfEntity> {
    @Override
    @Mapping(target = "conditionalNodes", source = "conditionalNodes", qualifiedByName = "mapNodes")
    @Mapping(target = "thenNodes", source = "thenNodes", qualifiedByName = "mapNodes")
    @Mapping(target = "elseNodes", source = "elseNodes", qualifiedByName = "mapNodes")
    NodeTreeIf toEntity(NodeTreeIfEntity schema);
}