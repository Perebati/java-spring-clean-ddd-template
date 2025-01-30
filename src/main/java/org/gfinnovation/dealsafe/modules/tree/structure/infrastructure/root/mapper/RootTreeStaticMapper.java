package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.mapper;

import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper.NodeTreeMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.RootTreeStaticEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

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
public interface RootTreeStaticMapper extends NodeTreeMapper<RootTreeStatic, RootTreeStaticEntity> {
    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTreeStatic toEntity(RootTreeStaticEntity schema);
}
