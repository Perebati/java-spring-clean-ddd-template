package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.modules.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonMultiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface ComparisonMultiMapper extends GenericBusinessMapper<ComparisonMulti, ComparisonMultiEntity> {
}
