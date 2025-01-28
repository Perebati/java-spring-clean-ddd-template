package org.gfinnovation.dealsafe.modules.tree.action.infrastructure;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.action.domain.ActionOperation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationActionMapper
 * @since 30/10/2024
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface ActionOperationMapper extends GenericBusinessMapper<ActionOperation, ActionOperationEntity> {
}
