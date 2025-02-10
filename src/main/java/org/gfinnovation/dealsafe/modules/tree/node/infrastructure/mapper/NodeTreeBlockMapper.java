package org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.tree.node.domain.NodeTreeBlock;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.NodeTreeBlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeBlockMapper
 * @since v1.0 (30/11/2024)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeBlockMapper extends NodeTreeGenericMapper<NodeTreeBlock, NodeTreeBlockEntity> {

    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    NodeTreeBlock toEntity(NodeTreeBlockEntity schema);
}