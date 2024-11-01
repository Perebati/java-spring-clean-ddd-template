package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericMapper;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.OperationActionSchema;
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
public interface OperationActionMapper extends GenericMapper<ActionOperationEntity, OperationActionSchema> {
}
