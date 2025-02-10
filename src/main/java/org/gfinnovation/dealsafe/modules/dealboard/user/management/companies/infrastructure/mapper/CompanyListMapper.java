package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.mapper.GenericBusinessMapper;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.infrastructure.CompanyListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Primary
public interface CompanyListMapper extends GenericBusinessMapper<CompanyList, CompanyListEntity> {
}
