package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

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
@Primary
public interface ComparisonSingularMapper extends GenericBusinessMapper<ComparisonSingular, ComparisonSingularEntity> {
}
