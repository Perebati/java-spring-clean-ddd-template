package org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.node.NodeTreeActionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface NodeTreeActionMapper
 * @since 22/01/2025
 */

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface NodeTreeActionMapper extends GenericBusinessMapper<NodeTreeAction, NodeTreeActionEntity> {
}