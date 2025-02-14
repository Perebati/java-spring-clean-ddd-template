package org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.input.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.Node;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.NodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class NodeMapper
 * @since 24/01/2025
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeMapper extends GenericBusinessMapper<Node<NodeInput>, NodeEntity> {
}
