package org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.mapper;

import org.gfinnovation.dealsafe._shared.infrastructure.GenericMapper;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.company.infrastructure.persistence.CompanySchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyMapper
 * @authorNote n/a
 * @since 30/10/2024
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper extends GenericMapper<CompanyEntity, CompanySchema> {
}