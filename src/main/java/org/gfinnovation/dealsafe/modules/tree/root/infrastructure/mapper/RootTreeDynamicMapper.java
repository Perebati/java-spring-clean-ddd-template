package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeGenericMapper;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeDynamicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeDynamicMapper
 * @since v1.0 (30/11/2024)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface RootTreeDynamicMapper extends NodeTreeGenericMapper<RootTreeDynamic, RootTreeDynamicEntity> {
    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTreeDynamic toEntity(RootTreeDynamicEntity schema);
}
