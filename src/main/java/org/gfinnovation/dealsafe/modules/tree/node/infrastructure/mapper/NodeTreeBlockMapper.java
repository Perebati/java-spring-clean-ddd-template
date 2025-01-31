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
 * @version DealSafe_alpha_v1
 * @interface NodeTreeBlockMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeBlockMapper extends NodeTreeMapper<NodeTreeBlock, NodeTreeBlockEntity> {

    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    NodeTreeBlock toEntity(NodeTreeBlockEntity schema);
}