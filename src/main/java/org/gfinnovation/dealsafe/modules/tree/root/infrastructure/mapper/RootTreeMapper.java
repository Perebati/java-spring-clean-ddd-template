package org.gfinnovation.dealsafe.modules.tree.root.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree.node.infrastructure.mapper.NodeTreeGenericMapper;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.RootTreeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeMapper
 * @since v1.0 (30/11/2024)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface RootTreeMapper extends NodeTreeGenericMapper<RootTree<NodeInput>, RootTreeEntity> {
    @Override
    @Mapping(target = "nodes", source = "nodes", qualifiedByName = "mapNodes")
    RootTree<NodeInput> toEntity(RootTreeEntity schema);
}