package org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ComparisonOperation;
import org.gfinnovation.dealsafe.modules.tree.operation.infrastructure.ComparisonOperationEntity;
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
public interface ComparisonOperationMapper extends GenericBusinessMapper<ComparisonOperation, ComparisonOperationEntity> {
}
