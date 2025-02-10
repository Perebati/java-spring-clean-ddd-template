package org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonCustomList;
import org.gfinnovation.dealsafe.modules.tree.comparison.infrastructure.ComparisonCustomListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class ComparisonCustomListMapper
 * @since v1.0 (06/02/2025)
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface ComparisonCustomListMapper extends GenericBusinessMapper<ComparisonCustomList, ComparisonCustomListEntity> {
}
