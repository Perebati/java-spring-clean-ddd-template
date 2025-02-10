package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonSingularEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class OperationComparisonMapper
 * @since v1.0 (30/11/2024)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface ComparisonSingularMapper extends GenericBusinessMapper<ComparisonSingular, ComparisonSingularEntity> {
}
