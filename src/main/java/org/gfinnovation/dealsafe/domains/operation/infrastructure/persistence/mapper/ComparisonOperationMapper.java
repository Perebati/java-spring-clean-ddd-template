package org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.persistence.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.infrastructure.persistence.ComparisonOperationSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface OperationComparisonMapper
 * @since 30/10/2024
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ComparisonOperationMapper extends GenericBusinessMapper<ComparisonOperationEntity, ComparisonOperationSchema> {
}
