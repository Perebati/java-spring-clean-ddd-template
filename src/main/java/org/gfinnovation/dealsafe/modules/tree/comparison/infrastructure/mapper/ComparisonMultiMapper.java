package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonMultiMapper
 * @since v1.0 (06/02/2025)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface ComparisonMultiMapper extends GenericBusinessMapper<ComparisonMulti, ComparisonMultiEntity> {
}
