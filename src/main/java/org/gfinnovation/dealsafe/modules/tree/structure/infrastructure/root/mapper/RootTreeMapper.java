package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.mapper;

import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTree;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper.NodeTreeMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.RootTreeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface RootTreeMapper
 * @since 30/10/2024
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface RootTreeMapper extends NodeTreeMapper<RootTree<?>, RootTreeEntity> {
    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTree<Object> toEntity(RootTreeEntity schema);
}