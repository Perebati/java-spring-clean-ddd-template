package org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ActionOperation;
import org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.ActionOperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

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
public interface ActionOperationMapper extends GenericBusinessMapper<ActionOperation, ActionOperationEntity> {
}
