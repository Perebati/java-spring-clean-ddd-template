package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.mapper;

import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeTree;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeTreeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeTreeMapper
 * @since v1.0 (10/02/2025)
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeMapper extends NodeTreeGenericMapper<NodeTree<NodeInput>, NodeTreeEntity> {

    @Override
    NodeTree<NodeInput> toEntity(NodeTreeEntity schema);
}