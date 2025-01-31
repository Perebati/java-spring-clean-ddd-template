package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeMapper;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeEntity;
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